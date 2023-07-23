package com.explicit.libraryManagementSystem;

import com.explicit.libraryManagementSystem.Entity.Members;
import com.explicit.libraryManagementSystem.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

	@Autowired
	private MemberRepository memberRepository;
	@Override
	public void run(String... args) throws Exception {
	}
}
