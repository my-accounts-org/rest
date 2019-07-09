package com.company.ac.services;

public interface Accounts {

	int CAPITAL_ACCOUNT=0; 	// Cr
    int LOANS=1;			// Cr
    int CURRENT_LIABILITIES=2;	// Cr
    int FIXED_ASSETS=3;		// Dr
    int IVESTMENTS=4;		// Dr
    int CURRENT_ASSETS=5;		// Dr
    int BRANCH_OR_DIVISIONS=6;	// Cr
    int MISC_EXPENSES=7;		// Dr
    int SUSPENSE_ACCOUNT=8;	// Cr	
    int SALES_ACCOUNT=9;		// Cr
    int PURCHASE_ACCOUNT=10;	// Dr
    int INDIRECT_INCOMES=11;	// Cr
    int INDIRECT_EXPENSES=12;  // Dr    
	
	public final String[] groups={
		"\'Capital Account\',0,4,null,null",//1 
		"\'Loans\',0,4,null,null",		//2
		"\'Current Liabilities\',0,4,null,null",	//3
		"\'Fixed Assets\',0,1,null,null",	//4
		"\'Investments\',0,1,null,null",		//5
		"\'Current Assets\',0,1,null,null",	//6
		"\'Branch/Divisions\',0,4,null,null",	//7
		"\'Misc. Expenses\',0,1,null,null",	//8
		"\'Suspence A/c\',0,4,null,null",		//9
		"\'Sales Accounts\',0,3,1,\'_SALES_\'",	//10
		"\'Purchase Accounts\',0,2,1,\'_PURCHASE_\'",	//11
		"\'Indirect Incomes\',0,3,0,null",	//12
		"\'Indirect Expenses\',0,2,0,null",	//13
		"\'Direct Income\',0,3,1,null", 		//14
		"\'Direct Expense\',0,2,1,null",		//15
		"\'Reserves & Surplus\',null,null",	//16 (group_name,group_nature,is_gross_affected) under->Capital
		"\'Banck OD A/c\',null,null",		//17 Under->Loans
		"\'Secured Loans\',null,null",	//18	Under->Loans
		"\'Unsecured Loans\',null,null",	//19  Under->Loans
		"\'Duties & Taxes\',null,null",	//20	Under->Current Liabilities
		"\'Provisions\',null,null",		//21  Under->Current Liabilities
		"\'Sundry Creditors\',null,null",	//22  Under->Current Liabilities
		"\'Stock-in-Hand\',null,null",		//23  Under->Current Assets
		"\'Deposits\',null,null",		//24  Under->Current Assets
		"\'Loans & Advances\',null,null",	//25  Under->Current Assets
		"\'Sundry Debtors\',null,null",		//26  Under->Current Assets
		"\'Cash-in-Hand\',null,null",		//27  Under->Current Assets
		"\'Bank Accounts\',null,null"		//28  Under->Current Assets
    };
}
