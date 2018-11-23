package com.involves.selecao.config;


import java.io.IOException;
import java.net.UnknownHostException;
import java.util.UUID;

import com.mongodb.Mongo;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.IFeatureAwareVersion;
import de.flapdoodle.embed.mongo.distribution.Version;

/**
 * This class encapsulates everything that would be needed to do embedded
 * MongoDB testing.
 */
public class MongodForTestsFactory {

	private static final Logger logger = LoggerFactory.getLogger(MongodForTestsFactory.class.getName());

	public static MongodForTestsFactory with(final IFeatureAwareVersion version) throws IOException {
		return new MongodForTestsFactory(version);
	}

	private final MongodExecutable mongodExecutable;

	private final MongodProcess mongodProcess;

	/**
	 * Create the testing utility using the latest production version of MongoDB.
	 * 
	 */
	public MongodForTestsFactory() throws IOException {
		this(Version.Main.PRODUCTION);
	}

	/**
	 * Create the testing utility using the specified version of MongoDB.
	 * 
	 * @param version version of MongoDB.
	 */
	public MongodForTestsFactory(final IFeatureAwareVersion version) throws IOException {

		final MongodStarter runtime = MongodStarter
				.getInstance(new RuntimeConfigBuilder().defaultsWithLogger(Command.MongoD, logger).build());
		mongodExecutable = runtime.prepare(newMongodConfig(version));
		mongodProcess = mongodExecutable.start();

	}

	protected IMongodConfig newMongodConfig(final IFeatureAwareVersion version) throws IOException {
		return new MongodConfigBuilder().version(version).build();
	}

	/**
	 * Creates a new Mongo connection.
	 * 
	 */
	public MongoClient newMongo() throws UnknownHostException {
		return new MongoClient(new ServerAddress(mongodProcess.getConfig().net().getServerAddress(),
				mongodProcess.getConfig().net().getPort()));
	}

	/**
	 * Creates a new DB with unique name for connection.
	 */
	@Deprecated
	public DB newDB(Mongo mongo) {
		return mongo.getDB(UUID.randomUUID().toString());
	}

	/**
	 * Creates a new DB with unique name for connection.
	 */
	public MongoDatabase newDatabase(MongoClient mongo) {
		return mongo.getDatabase(UUID.randomUUID().toString());
	}

	/**
	 * Cleans up the resources created by the utility.
	 */
	public void shutdown() {
		mongodProcess.stop();
		mongodExecutable.stop();
	}
}
