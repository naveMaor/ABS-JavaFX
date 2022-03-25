package Shai;

import nave.*;

import java.util.List;

import static nave.BackroundFunc.calculateDeposit;
import static nave.Database.getLoanList;

public class PrintFuncs {

    public static final void printLenderList(List<Lenders> lendersList)
    {
        for (Lenders lender:lendersList)
        {
            System.out.println(lender);
        }
    }
    public static final void printACTIVEstatus(Loan currLoan)
    {
        Timeline startLoanYaz = currLoan.getStartLoanYaz();
        Timeline paymentFrequency = currLoan.getPaymentFrequency();
        List<Payment> paymentsList = currLoan.getPaymentsList();
        double payedFund =currLoan.getPayedFund();
        double payedInterest = currLoan.getPayedInterest();
        double currFundDepth = currLoan.getPayedFund();
        double currInterestDepth =currLoan.getCurrInterestDepth();
        System.out.println("Loan start time" + startLoanYaz + "Yazes");
        int T = (Timeline.getCurrTime() - startLoanYaz.getTime()) % paymentFrequency.getTime();
        System.out.println("next payment: " + T);
        for(Payment pay:paymentsList)
        {
            System.out.println(pay.toString());
        }
        System.out.println("total payed fund: " + payedFund);
        System.out.println("total payed interest: " + payedInterest);
        System.out.println("remaining fund: " + currFundDepth);
        System.out.println("remaining interest: " + currInterestDepth);
    }
    public static final void printRISKstatus(Loan currLoan){
        printACTIVEstatus(currLoan);
        List<Payment> paymentsList = currLoan.getPaymentsList();
        int sumNotPayed = 0;
        int numNotPayed=0;
        for(Payment pays:paymentsList)
        {
            if(pays.isPayed() == false)
            {
                sumNotPayed+=pays.getFundPlusInterest();
                ++numNotPayed;
            }

        }
        System.out.println("num of delayed payments: " + numNotPayed);
        System.out.println("sum of delayed: " + sumNotPayed);
    }
    public final void printFINISHEDstatus(Loan currLoan){
        printLenderList();
        System.out.println("start loan yaz: "+startLoanYaz);
        System.out.println("end loan yaz" + endLoanYaz);
        for(Payment pay:paymentsList)
        {
            System.out.println(pay.toString());
        }
    }




    public final static void printAccountInfo(Account account)
    {
        List<Tnua> tnuaList = account.getTnuaList();
        int beforeBalance=account.getCurrBalance();
        int afterBalance=account.getCurrBalance();;
        for (Tnua tnua:tnuaList) {
            System.out.println("yaz of tnua: " + tnua.getTimeOfMovement() + "yazes");
            if (tnua.getSum() > 0) {
                System.out.println("schum tnua: +" + tnua.getTimeOfMovement());
            }
            else {
                System.out.println("schum tnua: " + tnua.getTimeOfMovement());
            }

            afterBalance +=tnua.getSum();
            System.out.println("balance before the tnua: " + beforeBalance);
            System.out.println("balance after the tnua: " + afterBalance);
            beforeBalance=afterBalance;
        }
    }
    public final static void printConnectedLoans(Client client)
    {
        String name = client.getFullName();
        List<Loan> l = client.getCleintAsLenderLoanList();
        if(!l.isEmpty())
        {
            System.out.println("this is the Loans that " + name + "is a lender:");
            for (Loan loan:l)
            {
                System.out.println("Loan Id: " + loan.getLoanID());
                System.out.println("Loan category: " + loan.getLoanCategory());
                System.out.println("loan original fund: " + loan.getLoanOriginalDepth());
                System.out.println("loan payment Frequency: " + loan.getPaymentFrequency());
                System.out.println("loan interest: " + loan.getOriginalInterest());
                System.out.println("total Loan Cost, Interest Plus Original Depth: " loan.getTotalLoanCostInterestPlusOriginalDepth());
                System.out.println("loan status: " + loan.getStatus());

            }
        }

    }

    public final static void PrintStatusConnectedLoans(Loan loan)
    {
        LoanStatus status=loan.getStatus();
        switch (status)
        {
            case PENDING:
            {
                double missingMoney = loan.getLoanOriginalDepth() - calculateDeposit(loan.getLendersList());
                System.out.println(missingMoney + "is missing in order to turn this loan active");
            }
            case ACTIVE:
            {

            }
        }
    }
}
