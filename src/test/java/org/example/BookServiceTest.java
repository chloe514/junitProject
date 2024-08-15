package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class BookServiceTest {
    @Mock
    private BookService bookService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchBook() {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book("Effective Java", "Joshua Bloch", "Programming", 45.0);
        books.add(book1);

        when(bookService.searchBook("Effective")).thenReturn(books);
        List<Book> result = bookService.searchBook("Effective");
        assertEquals(1, result.size());
        assertEquals("Effective Java", result.get(0).getTitle());
        verify(bookService).searchBook("Effective");
        System.out.println("Book search successful");
    }

    @Test
    public void testPurchaseBookSuccessfully() {
        User user = new User("Chloe", "password", "chloe@email.com");
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming", 45.0);

        when(bookService.purchaseBook(user, book)).thenReturn(true);
        boolean result = bookService.purchaseBook(user, book);
        assertTrue(result);
        verify(bookService).purchaseBook(user, book);
        System.out.println("Book purchase successful");
    }

    @Test
    public void testPurchaseBookNotInDatabase() {
        User user = new User("Chloe", "password", "chloe@email.com");
        Book book = new Book("Nonexistent Book", "Unknown Author", "Unknown", 0.0);

        when(bookService.purchaseBook(user, book)).thenReturn(false);
        boolean result = bookService.purchaseBook(user, book);
        assertFalse(result);
        verify(bookService).purchaseBook(user, book);
        System.out.println("Book not in database, purchase failed");
    }

    @Test
    public void testAddBookReviewSuccessfully() {
        User user = new User("Chloe", "password", "chloe@email.com");
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming", 45.0);
        user.getPurchasedBooks().add(book);

        when(bookService.addBookReview(user, book, "Great book!")).thenReturn(true);
        boolean result = bookService.addBookReview(user, book, "Great book!");
        assertTrue(result);
        verify(bookService).addBookReview(user, book, "Great book!");
        System.out.println("Book review added successfully");
    }

    @Test
    public void testAddBookReviewWithoutPurchase() {
        User user = new User("Chloe", "password", "chloe@email.com");
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming", 45.0);

        when(bookService.addBookReview(user, book, "Great book!")).thenReturn(false);
        boolean result = bookService.addBookReview(user, book, "Great book!");
        assertFalse(result);
        verify(bookService).addBookReview(user, book, "Great book!");
        System.out.println("Cannot add review without purchase");
    }
}


