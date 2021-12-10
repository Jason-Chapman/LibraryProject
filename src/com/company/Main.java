package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<String> bookInfo = new ArrayList<>();
    private static File LibraryFile = new File("Library.txt");

    public static void main(String[] args) throws Exception {

        try {
            if (LibraryFile.createNewFile()) {
                System.out.println("Library created: " + LibraryFile.getName());
            }
        } catch (IOException e) {
            System.out.println("Error found: " + e);
        }

        System.out.println("Welcome to the Library.");

        boolean loop = true;

        while (loop == true) {
            String menuSelect = getInput("1. Add Book\n2. View Books\n4. Delete Book\n5. Exit\n");
            switch (menuSelect) {
                case "1":
                    bookInfo.clear();
                    bookInfo.add(getBookInfo());
                    LibraryWrite();
                    break;

                case "2":
                    LibraryRead();
                    break;

                case "4":
                    String removedBook = getInput("Enter the book you want to remove in the format [Title, ISBN, Author, Genre]:\n");
                    RemoveBook(removedBook);
                    break;

                case "5":
                    loop = false;
            }
        }
    }

    public static String getBookInfo() {
        String bookTitle = getInput("Enter Book Title: ");
        String bookISBN = getInput("Enter Book ISBN: ");
        String bookAuthor = getInput("Enter Book Author: ");
        String bookGenre = getInput("Enter Book Genre: ");
        return (bookTitle + ", " + bookISBN + ", " + bookAuthor + ", " + bookGenre);
    }

    public static void LibraryWrite() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(LibraryFile.getName(), true);
        } catch (IOException e) {
            System.out.println("Error found: " + e);
        }

        try {
            fileWriter.write(String.valueOf(bookInfo) + "\n");
        } catch (IOException e) {
            System.out.println("Error found: " + e);
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error found: " + e);
        }

        System.out.println("\nBook successfully added to the Library.\n");
    }

    public static void LibraryRead() {
        try {
            Scanner libraryReader = new Scanner(LibraryFile);
            while (libraryReader.hasNextLine()) {
                String data = libraryReader.nextLine();
                System.out.println(data);
            }
            libraryReader.close();
            System.out.println("\n");
        } catch (FileNotFoundException e) {
            System.out.println("Error found: " + e);
        }
    }

    public static void RemoveBook(String book) throws Exception {
        File tempFile = new File("TemporaryFile.txt");
        FileWriter tempWriter = null;
        tempWriter = new FileWriter(tempFile.getName(), true);
        Scanner tempReader = new Scanner(tempFile);

        FileWriter libraryWriter = null;
        libraryWriter = new FileWriter(LibraryFile.getName(), false);
        Scanner libraryReader = new Scanner(LibraryFile);

        //~~~~~WHILE LOOP CONDITIONS NEVER TRUE, NEEDS FIX~~~~~\\

        while (libraryReader.hasNextLine()) {
            String data = libraryReader.nextLine();
            if(data.equals(book)) {
                continue;
            }
            else {
                tempWriter.write(data + "\n");
            }
        }
        while (tempReader.hasNextLine()) {
            String data = tempReader.nextLine();
            libraryWriter.write(data + "\n");
        }

        //~~~~~WHILE LOOP CONDITIONS NEVER TRUE, NEEDS FIX~~~~~\\

        tempWriter.close();
        tempReader.close();
        libraryWriter.close();
        libraryReader.close();

        System.out.println("Book successfully deleted.\n");

    }

    public static String getInput(String prompt) {
        System.out.print(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}