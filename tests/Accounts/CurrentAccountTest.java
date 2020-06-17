package Accounts;

import Exceptions.NegativeAmountException;
import Exceptions.NotEnoughMoneyException;


public class CurrentAccountTest{
    private static final String IBAN = "AFRT8723470912TRCFOPWR";
    private static final String OWNER_ID = "123";
    private static final double START_AMOUNT = 0;
    private static final double DEPOSIT_AMOUNT = 200;
    private static final double WITHDRAW_AMOUNT = 20;
    private static final double NEGATIVE_AMOUNT = -200;
    private static final double BIGGER_AMOUNT = 18824;
    private Account currentAccount;

    @Before
    public void setUp(){
        this.currentAccount = new CurrentAccount(IBAN,OWNER_ID,START_AMOUNT);
    }
    @Test
    public void testPositiveAmountDepositToAccount(){
        this.currentAccount.deposit(DEPOSIT_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, this.currentAccount.getAmount(),0,0);
    }
    @Test(expexted = NegativeAmountException.class)
    public void testNegativeAmountDepositToAccount(){
        this.currentAccount.deposit(NEGATIVE_AMOUNT)
    }
    @Test
    public void testPositiveAmountWithdrawFromAccount(){
        this.currentAccount.deposit(DEPOSIT_AMOUNT);
        this.currentAccount.withdraw(WITHDRAW_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT - WITHDRAW_AMOUNT, this.currentAccount.getAmount(),0.0);
    }
    @Test(expected = NegativeAmountException.class)
    public void testNegativeAmountWithdrawFromAccount(){
        this.currentAccount.withdraw(NEGATIVE_AMOUNT);
    }
    @Test(expected = NotEnoughMoneyException.class)
    public void testBiggerAmountWithdrawFromAccountThrowsException(){
        this.currentAccount.deposit(DEPOSIT_AMOUNT);
        this.currentAccount.widthdraw(BIGGER_AMOUNT);
    }
}
