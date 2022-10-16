import java.io.File

// Return what the user inputted
fun getUserInput(): Int {
    println("Enter an option:\n1. View Passwords \n2. Add password\n3. Exit")
    print("Option: ")
    return Integer.valueOf(readLine())
}

// Show the user the websites they have passwords saved for
fun viewWebsites(fileName : File) {
    println("Here are the current websites you have passwords saved for:")
    var websiteNames = fileName.readLines()
    // Get only the website names
    for (x in websiteNames) {
        println(x.split("|")[0])
    }
    println()
}

// View the passwords the user has after inputting a website
fun viewPasswords(fileName : File){
    print("Enter the website for the password: ")
    val newWeb = readLine().toString()
    var websiteNames = fileName.readLines()
    var found = 1
    for (x in websiteNames) {
        // Split the user and password and display
        if (newWeb == x.split("|")[0]) {
            println("Username " + found + ": " + x.split("|")[1])
            println("Password " + found + ": " + x.split("|")[2])
            found += 1
        }
    }
    // If the inputted website is not found, notify the user
    if (found == 1){
        println("Website " + newWeb + " is not saved")
    }
}

// Allow the user to add a password to their password file
fun addPassword(fileName : File) {
    print("Enter the website: ")
    val newWeb = readLine()
    print("Enter your new username: ")
    val newUsername = readLine()
    print("Enter your new password: ")
    val newPassword = readLine()
    // Append user info to file in format "WEBSITE|USERNAME|PASSWORD"
    fileName.appendText(newWeb.plus("|").plus(newUsername).plus("|").plus(newPassword).plus("\n"))
}

// Class for user actions including creating an account, logging in, and having a session
class User(){

    // Creates the user's account and adds it to the file
    fun createAccount(userFileName: File){
        print("Please enter your username: ")
        val username = readLine()
        print("Please enter your password: ") // add hashing
        val password = readLine()
        print("Please enter your password filename: ")
        val passwordFileName = readLine()
        userFileName.appendText(username.plus("|").plus(password).plus("|").plus(passwordFileName).plus("\n"))
        println("Account Creation Successful")
    }

    // Returns the user's password file if successful
    fun login(userFileName: File): File {
        println("Login")
        print("Please enter your username: ")
        val username = readLine()
        var userNames = userFileName.readLines()
        for (x in userNames) {
            if (x.split("|")[0] == username) {

                print("Password: ")
                val password = readLine()
                if (password == x.split("|")[1]) {
                    println("Login Successful.")
                    return File(x.split("|")[2])
                } else {
                    println("Incorrect password")
                    return File("FAILED")
                }
            }
        }
        println("Username does not exist")
        return File("FAILED")
    }

    // Session for user to view passwords or create new passwords
    fun session(passwordFileName: File) {    // add timeout
        var choice = 0
        while (choice < 3) {
            choice = getUserInput()

            if (choice == 1) {
                viewWebsites(passwordFileName)
                viewPasswords(passwordFileName)
            }
            else if (choice == 2) {
                addPassword(passwordFileName)
            }
            else {
                if (choice == 3){
                    return
                }
                println("Invalid Choice")
                choice = 0
            }
        }
    }
}

fun main() {
    // The user's filename
    val userFileName = File("users.txt")

    print("Enter 1 to login or 2 to create an account or anything else to quit: ")
    val userInput = readLine()
    if (userInput == "2"){
        val user = User()
        user.createAccount(userFileName)
        val passwordFileName = user.login(userFileName)
        if (passwordFileName != File("FAILED")) {
            user.session(passwordFileName)
        }

    }
    else if (userInput == "1") {
        val user = User()
        val passwordFileName = user.login(userFileName)
        if (passwordFileName != File("FAILED")) {
            user.session(passwordFileName)
        }
    } else {
        println("Goodbye")
        return
    }

}
