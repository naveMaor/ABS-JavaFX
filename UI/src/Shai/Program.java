package Shai;
import customes.Account;
import customes.Client;
import customes.Lenders;
import data.Database;
import loan.Loan;
import loan.enums.eLoanCategory;
import loan.enums.eLoanStatus;
import Money.operations.Payment;
import time.Timeline;

import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main (String[] args){

        //1ST TEST OBJ
        Loan test1 = new Loan(eLoanCategory.DEFICIT, eLoanStatus.ACTIVE,"Shai Shillo",new Timeline(10),new Timeline(1),new Timeline(1),10,1000);
        //Loan test1 = new Loan(eLoanCategory.DEFICIT, eLoanStatus.PENDING,"Shai Shillo",new Timeline(10),new Timeline(1),new Timeline(1),10,100000);
        test1.generateLoanID();

       //creates&sets lenders list:
        Lenders lender1=new Lenders("Idan",250);
        Lenders lender2=new Lenders("Shai", 350);
        Lenders lender3=new Lenders("Nave",400);
        List<Lenders> lendersList1 = new ArrayList<>(3);
        lendersList1.add(lender1);
        lendersList1.add(lender2);
        lendersList1.add(lender3);
        test1.setLendersList(lendersList1);

        //creates&sets payment list:
        Payment payment1 = new Payment(new Timeline(2),true,100,10);
        Payment payment2 = new Payment(new Timeline(2),true,100,10);
        Payment payment3 = new Payment(new Timeline(2),true,100,10);
        List<Payment> paymentsList1 = new ArrayList<>(3);
        paymentsList1.add(payment1);
        paymentsList1.add(payment2);
        paymentsList1.add(payment3);
        test1.setPaymentsList(paymentsList1);

        //set remaining Loan fields:


        //2ST TEST OBJ
        Loan test2 = new Loan(eLoanCategory.EVENT, eLoanStatus.NEW,"Oded Menashe",new Timeline(10),new Timeline(1),new Timeline(2),7.5,1000);
        test1.generateLoanID();

        //creates&sets lenders list:
        Lenders lender4=new Lenders("shlomi",250);
        Lenders lender5=new Lenders("eran", 150);
        Lenders lender6=new Lenders("gadi",600);
        List<Lenders> lendersList2 = new ArrayList<>(3);
        lendersList2.add(lender4);
        lendersList2.add(lender5);
        lendersList2.add(lender6);
        test2.setLendersList(lendersList2);

        //creates&sets payment list:
        Payment payment4 = new Payment(new Timeline(2),true,200,15);
        Payment payment5 = new Payment(new Timeline(4),true,200,15);
        Payment payment6 = new Payment(new Timeline(6),false,200,15);
        List<Payment> paymentsList2 = new ArrayList<>(3);
        paymentsList2.add(payment4);
        paymentsList2.add(payment5);
        paymentsList2.add(payment6);
        test2.setPaymentsList(paymentsList2);

        Database.addLoanToLoanList(test1);
        Database.addLoanToLoanList(test2);

        //User_interface.allLoansData();
        //set remaining Loan fields:

        //creating clients for test
        Client idan = new Client("Idan",new Account(204684914, 5000));
        Client shai = new Client("Shai",new Account(204684914, 5000));
        Client nave = new Client("Nave",new Account(204684914, 5000));
        // adding new test clients to map
        Database.addClientToClientList(idan);
        Database.addClientToClientList(shai);
        Database.addClientToClientList(nave);
        // User_interface.func4();
        // User_interface.func5();
    //testing func payLoanDividendsToLenders
        test1.payLoanDividendsToLenders();
        System.out.println(idan.getMyAccount().toString()+"\n"+shai.getMyAccount().toString()+"\n"+nave.getMyAccount().toString());
     test1.setLoanAccount(new Account(204684914,0));
     test2.setLoanAccount((new Account(204684915,0)));
        User_interface.func6();
    }

}
