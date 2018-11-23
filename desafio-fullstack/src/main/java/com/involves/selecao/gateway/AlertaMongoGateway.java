package com.involves.selecao.gateway;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.involves.selecao.domain.Alerta;
import com.involves.selecao.gateway.mongo.MongoDbFactory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@Component
public class AlertaMongoGateway implements AlertaGateway{
	
	@Autowired
	private MongoDbFactory mongoFactory;

	@Override
	public void salvar(Alerta alerta) {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("Alertas");
		Document doc = new Document("ponto_de_venda", alerta.getPontoDeVenda())
                .append("descricao", alerta.getDescricao())
                .append("tipo", alerta.getFlTipo())
                .append("margem", alerta.getMargem())
                .append("produto", alerta.getProduto())
				.append("categoria", alerta.getCategoria());
		collection.insertOne(doc);
	}

	@Override
	public List<Alerta> buscarTodos(Alerta alerta) {
		FindIterable<Document> db = null;
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("Alertas");
		
		if (alerta.getFlTipo() != null) {
			db = collection.find(Filters.eq("tipo", alerta.getFlTipo()));
		} else if (alerta.getPontoDeVenda() != null) {
			db = collection.find(Filters.regex("ponto_de_venda", Pattern.compile("(?i).*" + alerta.getPontoDeVenda() + ".*")));
		} else {
			db = collection.find();
		}

		List<Alerta> alertas = new ArrayList<>();
		if (db != null) {
			for (Document document : db) {
				Alerta alertaRetorno = new Alerta();
				alertaRetorno.setDescricao(document.getString("descricao"));
				alertaRetorno.setFlTipo(document.getInteger("tipo"));
				alertaRetorno.setMargem(document.getInteger("margem"));
				alertaRetorno.setDataHoraCadastro(ZonedDateTime.ofInstant(new Timestamp(document.getObjectId("_id").getDate().getTime()).toInstant(), ZoneId.systemDefault()));
				alertaRetorno.setPontoDeVenda(document.getString("ponto_de_venda"));
				alertaRetorno.setProduto(document.getString("produto"));
				alertaRetorno.setCategoria(document.getString("categoria"));
				alertaRetorno.setId(document.getObjectId("_id").toHexString());
				alertas.add(alertaRetorno);
			}
		}
		return alertas;
	}
}
