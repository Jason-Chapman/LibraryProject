package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<String> bookInfo = new ArrayList<>();
    private static ArrayList<String> listOfBooks = new ArrayList<>();
    private static ArrayList<String> BorrowerInfo = new ArrayList<>();
    private static ArrayList<String> listOfBorrowers = new ArrayList<>();
    private static File LibraryFile = new File("Library.txt");
    private static File BorrowersFile = new File("Borrowers.txt");

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


            String menuSelect = getInput("1. Add Book\n2. View Books\n3. Edit Book\n4. Delete Book\n5. Add Borrower\n6. View Borrowers\n7. Edit Borrower\n8. Delete Borrower\n9. Exit\n");
            switch (menuSelect) {
                case "1":
                    Books book = new Books(getInput("Enter Book Title: "), getInput("Enter Book ISBN: "), getInput("Enter Book Author: "), getInput("Enter Book Genre: "));
                    LibraryWrite();
                    break;

                case "2":
                    LibraryRead();
                    break;

                case "3":
                    String uneditedBook = getInput("(Type in the format [Title, ISBN, Author, Genre])\nEnter the book you want to edit: \n");
                    String editedBook = getInput("(Type in the format [Title, ISBN, Author, Genre])\nEnter the new book details: \n");
                    LibraryEdit(uneditedBook, editedBook);
                    break;

                case "4":
                    String removedBook = getInput("(Type in the format [Title, ISBN, Author, Genre])\nEnter the book you want to delete: \n");
                    RemoveBook(removedBook);
                    break;

                case "5":
                    BorrowerInfo.clear();
                    BorrowerInfo.add(getBorrowerInfo());
                    BorrowerWrite();
                    break;

                case "6":
                    BorrowerRead();
                    break;


                case "7":
                    String uneditedBorrower = getInput("(Type in the format [First Name, Last Name, Title Of Book Borrowed])\nEnter the borrower you want to edit: \n");
                    String editedBorrower = getInput("(Type in the format [First Name, Last Name, Title Of Book Borrowed])\nEnter the new borrower details: \n");
                    BorrowerEdit(uneditedBorrower, editedBorrower);
                    break;

                case "8":
                    String removedBorrower = getInput("(Type in the format [First Name, Last Name, Title Of Book Borrowed])\nEnter the borrower you want to delete: \n");
                    RemoveBorrower(removedBorrower);
                    break;

                case "9":
                    loop = false;
            }
        }
    }

    public static String getBorrowerInfo() {
        String borrowerFirstName = getInput("Enter First Name: ");
        String borrowerLastName = getInput("Enter Last Name: ");
        String borrowerBook = getInput("Enter Title Of Book Borrowed: ");
        return (borrowerFirstName + ", " + borrowerLastName + ", " + borrowerBook);
    }

    public static void LibraryWrite() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(LibraryFile.getName(), true);
        } catch (IOException e) {
            System.out.println("Error found: " + e);
        }

        try {
            fileWriter.write(String.valueOf(book) + "\n");
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

    public static void BorrowerWrite() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(BorrowersFile.getName(), true);
        } catch (IOException e) {
            System.out.println("Error found: " + e);
        }

        try {
            fileWriter.write(String.valueOf(BorrowerInfo) + "\n");
        } catch (IOException e) {
            System.out.println("Error found: " + e);
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error found: " + e);
        }

        System.out.println("\nBorrower successfully added to the Library.\n");
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

    public static void BorrowerRead() {
        try {
            Scanner borrowerReader = new Scanner(BorrowersFile);
            while (borrowerReader.hasNextLine()) {
                String data = borrowerReader.nextLine();
                System.out.println(data);
            }
            borrowerReader.close();
            System.out.println("\n");
        } catch (FileNotFoundException e) {
            System.out.println("Error found: " + e);
        }
    }

    public static void LibraryEdit(String oldBook, String newBook) throws Exception {
        File tempFile = new File("myTempFile.txt");

        BufferedReader libraryReader = new BufferedReader(new FileReader(LibraryFile));
        BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile));

        String lineToEdit = oldBook;
        String currentLine;

        while ((currentLine = libraryReader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToEdit)) {
                tempWriter.write(newBook + "\n");
            } else {
                tempWriter.write(currentLine + "\n");
            }
        }

        tempWriter.close();
        libraryReader.close();

        try (FileReader fileReader = new FileReader("myTempFile.txt");
             FileWriter fileWriter = new FileWriter("Library.txt")) {
            int c = fileReader.read();
            while(c!=-1) {
                fileWriter.write(c);
                c = fileReader.read();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void BorrowerEdit(String oldBorrower, String newBorrower) throws Exception {
        File tempFile = new File("myTempFile.txt");

        BufferedReader borrowerReader = new BufferedReader(new FileReader(BorrowersFile));
        BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile));

        String lineToEdit = oldBorrower;
        String currentLine;

        while ((currentLine = borrowerReader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToEdit)) {
                tempWriter.write(newBorrower + "\n");
            } else {
                tempWriter.write(currentLine + "\n");
            }
        }

        tempWriter.close();
        borrowerReader.close();

        try (FileReader fileReader = new FileReader("myTempFile.txt");
             FileWriter fileWriter = new FileWriter("Borrowers.txt")) {
            int c = fileReader.read();
            while(c!=-1) {
                fileWriter.write(c);
                c = fileReader.read();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void RemoveBook(String book) throws Exception {
        File tempFile = new File("myTempFile.txt");

        BufferedReader libraryReader = new BufferedReader(new FileReader(LibraryFile));
        BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = book;
        String currentLine;

        while ((currentLine = libraryReader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) {
                continue;
            }
            tempWriter.write(currentLine + "\n");
        }

        tempWriter.close();
        libraryReader.close();

        try (FileReader fileReader = new FileReader("myTempFile.txt");
             FileWriter fileWriter = new FileWriter("Library.txt")) {
            int c = fileReader.read();
            while(c!=-1) {
                fileWriter.write(c);
                c = fileReader.read();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void RemoveBorrower(String borrower) throws Exception {
        File tempFile = new File("myTempFile.txt");

        BufferedReader borrowerReader = new BufferedReader(new FileReader(BorrowersFile));
        BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = borrower;
        String currentLine;

        while ((currentLine = borrowerReader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) {
                continue;
            }
            tempWriter.write(currentLine + "\n");
        }

        tempWriter.close();
        borrowerReader.close();

        try (FileReader fileReader = new FileReader("myTempFile.txt");
             FileWriter fileWriter = new FileWriter("Borrowers.txt")) {
            int c = fileReader.read();
            while(c!=-1) {
                fileWriter.write(c);
                c = fileReader.read();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

//    public static Object GetData(File filename) {
//        try {
//            Scanner fileReader = new Scanner(filename);
//            while (fileReader.hasNextLine()) {
//                String data = fileReader.nextLine();
//                System.out.println(data);
//            }
//            fileReader.close();
//            System.out.println("\n");
//        } catch (FileNotFoundException e) {
//            System.out.println("Error found: " + e);
//        }
//    }

    public static String getInput(String prompt) {
        System.out.print(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}