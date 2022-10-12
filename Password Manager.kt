import java.io.File

fun getUserInput(): Int {
    println("Enter an option:\n1. View Passwords \n2. Add password\n3. Exit")
    print("Option: ")
    return Integer.valueOf(readLine())
}

fun viewPasswords(fileName : File) {
    println(fileName.readLines()) //print only website
}

fun addPassword(fileName : File) {
    print("Enter the website: ")
    val newWeb = readLine()
    print("Enter your new username: ")
    val newUsername = readLine()
    print("Enter your new password: ")
    val newPassword = readLine()
    fileName.appendText(newWeb.plus(":").plus(newUsername).plus(":").plus(newPassword).plus("\n"))
}

fun main(args: Array<String>) {
    val username1 = "dax"
    val userPassword = "password" //add timeout for login
    val fileName = File("passwords.txt")

    print("Please Enter your username: ")
    val username = readLine()
    if (username == username1) {
        print("Password: ")
        val password = readLine()
        if (password == userPassword) {
            println("Login Successful.")
            var choice = 0
            while (choice != 3) {
                choice = getUserInput()

                if (choice == 1) {
                    viewPasswords(fileName)
                }
                else if (choice == 2) {
                    addPassword(fileName)
                }
                else {
                    print("Invalid Choice")
                    choice = 0
                }
            }
        } else {
            println("Incorrect password")
        }
    } else {
        println("Username does not exist")
    }

}
