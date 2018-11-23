package com.involves.selecao.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.domain.Pesquisa;
import com.involves.selecao.service.BuscaDePesquisasService;

@Service
public class BuscaDePesquisasServiceImpl implements BuscaDePesquisasService {
	
	private static final String URL_PESQUISAS = "https://selecao-involves.agilepromoter.com/pesquisas";
	
	public List<Pesquisa> getPesquisasFromJSON() {
		URL url;
		Pesquisa[] ps;
		try {
			url = new URL(URL_PESQUISAS);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuffer content = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			Gson gson = new Gson();
			ps = gson.fromJson(content.toString(), Pesquisa[].class);
			return Arrays.asList(ps);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<Pesquisa>();
	}
}
