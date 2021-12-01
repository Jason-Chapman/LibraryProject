package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<String> bookInfo = new ArrayList<>();
    private static File fileObject = new File("Library.txt");

    public static void main(String[] args) {

        try {
            if (fileObject.createNewFile()) {
                System.out.println("Library created: " + fileObject.getName());
            }
        } catch (IOException e) {
            System.out.println("Error found: " + e);
        }

        System.out.println("Welcome to the Library.");

        boolean loop = true;

        while (loop == true) {
            String menuSelect = getInput("1. Add Book\n2. View Books\n3. Exit\n");
            switch (menuSelect) {
                case "1":
                    bookInfo.clear();
                    bookInfo.add(getBookInfo());
                    LibraryWrite();
                    break;

                case "2":
                    LibraryRead();
                    break;

                case "3":
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
            fileWriter = new FileWriter(fileObject.getName(), true);
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
            Scanner libraryReader = new Scanner(fileObject);
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

    public static String getInput(String prompt) {
        System.out.print(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}