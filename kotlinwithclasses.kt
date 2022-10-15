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
    print("Enter the website: ")
    val newWeb = readLine().toString()
    var websiteNames = fileName.readLines()
    for (x in websiteNames) {
        if (newWeb == x.split("|")[0]) {
            println("Username: " + x.split("|")[1])
            println("Username: " + x.split("|")[2])
        }
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

class User (_username: String,
            _password: String) {
    val username = _username
    val password = _password
    init {
        print("Welcome user $username")
    }

    class PasswordFile {
        val userPasswordFile = File("passwords.txt")
    }

    private fun getUsername(): String {

    }
    private fun getPassword(): String {

    }
}



fun main(args: Array<String>) {
    print("Please enter your username: ")
    val username = readLine().toString()
    print("Password: ")
    val password = readLine().toString()

    val user = User(username, password)

    viewWebsites(fileName)

            var choice = 0
            while (choice < 3) {
                choice = getUserInput()

                if (choice == 1) {
                    viewPasswords(fileName)
                }
                else if (choice == 2) {
                    addPassword(fileName)
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
    } else {
        println("Username does not exist")
    }

}
