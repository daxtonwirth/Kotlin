import java.io.File

fun getUserInput(): Int {
    println("Enter an option:\n1. View Passwords \n2. Add password\n3. Exit")
    print("Option: ")
    return Integer.valueOf(readLine())
}

fun viewWebsites(fileName : File) {
    println("Here are the current websites you have passwords saved for:")
    var websiteNames = fileName.readLines()
    for (x in websiteNames) {
        println(x.split("|")[0])
    }
    println()
}

fun viewPasswords(fileName : File){
    print("Enter the website for the password: ")
    val newWeb = readLine().toString()
    var websiteNames = fileName.readLines()
    var found = 1
    for (x in websiteNames) {
        if (newWeb == x.split("|")[0]) {
            println("Username " + found + ": " + x.split("|")[1])
            println("Password " + found + ": " + x.split("|")[2])
            found += 1
        }
    }
    if (found == 1){
        println("Website " + newWeb + " is not saved")
    }
}

fun addPassword(fileName : File) {
    print("Enter the website: ")
    val newWeb = readLine()
    print("Enter your new username: ")
    val newUsername = readLine()
    print("Enter your new password: ")
    val newPassword = readLine()
    fileName.appendText(newWeb.plus("|").plus(newUsername).plus("|").plus(newPassword).plus("\n"))
}

fun main() {
    //add timeout for login
    val userFileName = File("users.txt")

    print("Enter 1 to login or 2 to create an account or anything else to quit: ")
    val userInput = readLine()
    if (userInput == "2"){
        print("Please enter your username: ")
        val username = readLine()
        print("Please enter your password: ") // add hashing
        val password = readLine()
        print("Please enter your password filename: ")
        val passwordFileName = readLine()
        userFileName.appendText(username.plus("|").plus(password).plus("|").plus(passwordFileName).plus("\n"))
        println("Account Creation Successful")
    }

    println("Login")
    print("Please enter your username: ")
    val username = readLine()

    var userNames = userFileName.readLines()
    var found = 0
    for (x in userNames) {
        if (x.split("|")[0] == username) {
            found += 1

            print("Password: ")
            val password = readLine()
            if (password == x.split("|")[1]) {
                println("Login Successful.")
                val passwordFileName = File(x.split("|")[2])

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
            } else {
                println("Incorrect password")
            }



        }
    }
    if (found == 0) {
        println("Username does not exist")
    }


}
