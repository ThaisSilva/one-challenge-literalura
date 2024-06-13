package com.alura.literalura;

import com.alura.literalura.service.AuthorService;
import com.alura.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);

			while (true) {
				System.out.println("""
									Escolha uma opção:
						1. Buscar Livro
						2. Buscar Autor
						3. Listar Livros Buscados
						4. Listar Autores Buscados
						5. Listar Autores Vivos
						6. Listar Livros no Idioma
						0. Sair""");

				int option = scanner.nextInt();
				scanner.nextLine();

				switch (option) {
					case 1:
						System.out.println("Digite o título do livro:");
						String title = scanner.nextLine();
						bookService.fetchBookByTitle(title);
						break;
					case 2:
						System.out.println("Digite o nome do autor:");
						String author = scanner.nextLine();
						authorService.fetchAuthorByName(author);
						break;
					case 3:
						bookService.listSearchedBooks();
						break;
					case 4:
						authorService.listSearchedAuthors();
						break;
					case 5:
						authorService.listAliveAuthors();
						break;
					case 6:
						bookService.listBooksByLanguages();
						break;
					case 0:
						System.out.println("Saindo...");
						return;
					default:
						System.out.println("Opção inválida!");
				}
		}
	}
}
