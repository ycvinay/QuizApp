package com.example.quizapp.database;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.example.quizapp.dao.QuestionDao;
import com.example.quizapp.model.Question;

public class DatabaseInitializer {

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void populateDB(final QuizDB db) {
        executorService.execute(() -> {
            QuestionDao dao = db.questionDao();

            db.clearAllTables();
            // C Questions
            dao.insertQuestion(new Question("Which of the following is a valid variable declaration in C?", "int x;", "integer x;", "x = int;", "var x;", "int x;", "C"));
            dao.insertQuestion(new Question("What does the 'printf' function do in C?", "Prints formatted output", "Reads input from the user", "Allocates memory", "Compares two values", "Prints formatted output", "C"));
            dao.insertQuestion(new Question("What is the purpose of the `main` function in a C program?", "To start the execution of the program", "To define global variables", "To include header files", "To end the execution of the program", "To start the execution of the program", "C"));
            dao.insertQuestion(new Question("Which of the following is not a valid data type in C?", "int", "float", "string", "char", "string", "C"));
            dao.insertQuestion(new Question("What is the size of an `int` in C on a 32-bit system?", "2 bytes", "4 bytes", "8 bytes", "16 bytes", "4 bytes", "C"));
            dao.insertQuestion(new Question("Which of the following is used to input values in C?", "scanf", "printf", "cin", "input", "scanf", "C"));
            dao.insertQuestion(new Question("Which operator is used to access the value of a pointer in C?", "*", "&", "^", "@", "*", "C"));
            dao.insertQuestion(new Question("Which of the following loops is entry-controlled in C?", "for", "do-while", "while", "goto", "for", "C"));
            dao.insertQuestion(new Question("Which of the following is a storage class in C?", "auto", "loop", "module", "class", "auto", "C"));
            dao.insertQuestion(new Question("Which function is used to compare two strings in C?", "strcmp", "strcat", "strlen", "strcpy", "strcmp", "C"));

            // Java Questions
            dao.insertQuestion(new Question("Which keyword is used to create a class in Java?", "class", "new", "function", "create", "class", "Java"));
            dao.insertQuestion(new Question("What is the default value of a boolean variable in Java?", "false", "true", "0", "null", "false", "Java"));
            dao.insertQuestion(new Question("Which access modifier allows access only within the same package in Java?", "public", "protected", "private", "default", "default", "Java"));
            dao.insertQuestion(new Question("What does JVM stand for?", "Java Virtual Machine", "Java Variable Method", "Java Visual Machine", "Java Validation Method", "Java Virtual Machine", "Java"));
            dao.insertQuestion(new Question("Which of the following keywords is used to create an instance of a class in Java?", "new", "create", "instance", "class", "new", "Java"));
            dao.insertQuestion(new Question("What is the size of an int in Java?", "2 bytes", "4 bytes", "8 bytes", "16 bytes", "4 bytes", "Java"));
            dao.insertQuestion(new Question("Which method is used to compare two strings in Java?", "compare", "equals", "compareTo", "strcompare", "compareTo", "Java"));
            dao.insertQuestion(new Question("What is an abstract class in Java?", "A class that cannot be instantiated", "A class with all static methods", "A class with all final methods", "A class with no methods", "A class that cannot be instantiated", "Java"));
            dao.insertQuestion(new Question("Which of the following is not a primitive type in Java?", "int", "String", "float", "boolean", "String", "Java"));
            dao.insertQuestion(new Question("Which of the following is used to handle exceptions in Java?", "try-catch", "if-else", "for loop", "switch case", "try-catch", "Java"));

            // Python Questions
            dao.insertQuestion(new Question("How do you start a comment in Python?", "#", "//", "/*", "<!--", "#", "Python"));
            dao.insertQuestion(new Question("What is the output of 'print(2 ** 3)' in Python?", "8", "6", "5", "9", "8", "Python"));
            dao.insertQuestion(new Question("Which of the following is used to create a function in Python?", "def", "function", "create", "func", "def", "Python"));
            dao.insertQuestion(new Question("What does 'len()' do in Python?", "Returns the length of an object", "Converts to a list", "Adds two numbers", "Deletes an object", "Returns the length of an object", "Python"));
            dao.insertQuestion(new Question("How do you indicate the end of a block of code in Python?", "Indentation", "Curly braces", "Semicolon", "End keyword", "Indentation", "Python"));
            dao.insertQuestion(new Question("Which keyword is used to define a loop in Python?", "for", "loop", "repeat", "iterate", "for", "Python"));
            dao.insertQuestion(new Question("What is the output of `len([1, 2, 3])` in Python?", "3", "2", "1", "4", "3", "Python"));
            dao.insertQuestion(new Question("Which of the following is used to read input in Python?", "input()", "scanf()", "read()", "getchar()", "input()", "Python"));
            dao.insertQuestion(new Question("Which of the following data structures is immutable in Python?", "list", "tuple", "set", "dictionary", "tuple", "Python"));
            dao.insertQuestion(new Question("Which of the following is used to handle exceptions in Python?", "try-except", "if-else", "for-else", "switch-except", "try-except", "Python"));

            // JavaScript Questions
            dao.insertQuestion(new Question("Which method is used to add an element to the end of an array in JavaScript?", "push()", "pop()", "shift()", "unshift()", "push()", "JavaScript"));
            dao.insertQuestion(new Question("What does 'console.log' do in JavaScript?", "Logs information to the console", "Displays an alert box", "Writes to the document", "Executes code in a loop", "Logs information to the console", "JavaScript"));
            dao.insertQuestion(new Question("How do you declare a variable in JavaScript?", "var", "let", "const", "All of the above", "All of the above", "JavaScript"));
            dao.insertQuestion(new Question("Which operator is used to concatenate strings in JavaScript?", "+", "-", "*", "/", "+", "JavaScript"));
            dao.insertQuestion(new Question("How can you create an array in JavaScript?", "var arr = []", "array arr = {}", "new Array()", "Both A and C", "Both A and C", "JavaScript"));
            dao.insertQuestion(new Question("Which of the following is not a JavaScript data type?", "Number", "String", "Array", "Boolean", "Array", "JavaScript"));
            dao.insertQuestion(new Question("What does the '===' operator do in JavaScript?", "Strictly compares values", "Assigns values", "Adds values", "Compares types only", "Strictly compares values", "JavaScript"));
            dao.insertQuestion(new Question("Which method removes the last element from an array in JavaScript?", "pop()", "push()", "shift()", "unshift()", "pop()", "JavaScript"));
            dao.insertQuestion(new Question("Which keyword is used to declare a constant in JavaScript?", "const", "var", "let", "final", "const", "JavaScript"));
            dao.insertQuestion(new Question("Which of the following loops in JavaScript is entry-controlled?", "for", "do-while", "while", "switch", "for", "JavaScript"));

            // HTML Questions
            dao.insertQuestion(new Question("Which HTML tag is used to define an internal style sheet?", "<style>", "<css>", "<script>", "<link>", "<style>", "HTML"));
            dao.insertQuestion(new Question("What does the 'alt' attribute of an <img> tag specify in HTML?", "Alternative text for the image", "The image's source URL", "The image's width", "The image's height", "Alternative text for the image", "HTML"));
            dao.insertQuestion(new Question("Which HTML element defines the title of a document?", "<head>", "<title>", "<meta>", "<body>", "<title>", "HTML"));
            dao.insertQuestion(new Question("What is the purpose of the <div> tag in HTML?", "To define a division or section", "To display an image", "To create a hyperlink", "To format text", "To define a division or section", "HTML"));
            dao.insertQuestion(new Question("How do you create a hyperlink in HTML?", "<a href='url'>Link</a>", "<link href='url'>Link</link>", "<hyperlink url='url'>Link</hyperlink>", "<a url='url'>Link</a>", "<a href='url'>Link</a>", "HTML"));
            dao.insertQuestion(new Question("Which HTML tag is used to insert a line break?", "<br>", "<break>", "<lb>", "<hr>", "<br>", "HTML"));
            dao.insertQuestion(new Question("What is the correct format to set the background color in HTML?", "style='background-color: red;'", "bg-color='red'", "background-color='red'", "set-bg='red'", "style='background-color: red;'", "HTML"));
            dao.insertQuestion(new Question("Which attribute specifies the URL of an image in an <img> tag?", "src", "href", "alt", "link", "src", "HTML"));
            dao.insertQuestion(new Question("Which HTML tag is used to create a dropdown list?", "<select>", "<dropdown>", "<list>", "<menu>", "<select>", "HTML"));
            dao.insertQuestion(new Question("Which attribute is used to uniquely identify an HTML element?", "id", "class", "name", "key", "id", "HTML"));

        });
    }
}
