package com.bank.kantor;

import com.bank.kantor.kantor.Kantor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class KantorApplication {

	public static void main(String[] args) {

		SpringApplication.run(KantorApplication.class, args);
	}

}
