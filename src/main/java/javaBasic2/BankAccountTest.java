package javaBasic2;

public class BankAccountTest {

    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwnerName("Kirill");
        bankAccount.setBalance(1000);
        bankAccount.setBalance(-500);
        System.out.println("У аккаунта " + bankAccount.getOwnerName() + " баланс: " + bankAccount.getBalance());
    }
}
