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
        }
        catch (IOException e) {
            System.out.println("Error found: " + e);
        }

        bookInfo.add(getBookInfo());
    }

    public static String getBookInfo() {
        String bookTitle = getInput("Enter Book Title: ");
        String bookISBN = getInput("Enter Book ISBN: ");
        String bookAuthor = getInput("Enter Book Author: ");
        String bookGenre = getInput("Enter Book Genre: ");
        return (bookTitle + "," + bookISBN + "," + bookAuthor + "," + bookGenre);
    }

    public static void LibraryWrite() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileObject.getName(), true);
        } catch (IOException e) {
            System.out.println("Error found: " + e);
        }

        try {
            fileWriter.write(String.valueOf(bookInfo));
        } catch (IOException e) {
            System.out.println("Error found: " + e);
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error found: " + e);
        }

        System.out.println("Successfully wrote to the Library.");
    }

//--------------------------------------------- IN PROGRESS ---------------------------------------------\\

    public static void LibraryRead() {
        try {
            Scanner myReader = new Scanner(fileObject);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

//---------------------------------------------------------------------------------------------------------\\

    public static String getInput(String prompt) {
        System.out.print(prompt);
        Scanner input = new Scanner(System.in);
        return input.next();
    }
}