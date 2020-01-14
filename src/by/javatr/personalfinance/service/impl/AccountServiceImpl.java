package by.javatr.personalfinance.service.impl;

import by.javatr.personalfinance.bean.User;
import by.javatr.personalfinance.service.AccountService;
import by.javatr.personalfinance.service.exception.ServiceException;

public class AccountServiceImpl implements AccountService {
    @Override
    public void singIn(String login, String password) throws ServiceException {

    }

    @Override
    public void singOut(String login) throws ServiceException {

    }

    @Override
    public void registration(User user, String password) throws ServiceException {

    }/*

    private AccountServiceImpl() {}

    public Account createAccount(String name, AccountTypes accountType, AccountStatuses status,
                                 boolean favourite, int currencyId) {
        Account account = Account.create(name, accountType, status, favourite, currencyId);

        // updateData
        AccountRepository repo = new AccountRepository(getContext());
        repo.save(account);

        return account;
    }

    *//**
     * Loads account list, applying the current preferences for Open & Favourite accounts.
     * @return List of accounts
     *//*
    public List<Account> getAccountList() {
        AppSettings settings = new AppSettings(getContext());

        boolean favourite = settings.getLookAndFeelSettings().getViewFavouriteAccounts();
        boolean open = settings.getLookAndFeelSettings().getViewOpenAccounts();

        return getAccountList(open, favourite);
    }

    *//**
     * Load account list with given parameters.
     * Includes all account types.
     * @param open     show open accounts
     * @param favorite show favorite account
     * @return List<Account> list of accounts selected
     *//*
    public List<Account> getAccountList(boolean open, boolean favorite) {
        // create a return list
        return loadAccounts(open, favorite, null);
    }

    *//**
     * Calculate simple balance by adding together all transactions before and on the
     * given date. To get the real balance, this amount should be subtracted from the
     * account initial balance.
     * @param isoDate date in ISO format
     *//*
    public Money calculateBalanceOn(int accountId, String isoDate) {
        Money total = MoneyFactory.fromBigDecimal(BigDecimal.ZERO);

        WhereStatementGenerator where = new WhereStatementGenerator();
        // load all transactions on the account before and on given date.
        where.addStatement(
                where.concatenateOr(
                        where.getStatement(ITransactionEntity.ACCOUNTID, "=", accountId),
                        where.getStatement(ITransactionEntity.TOACCOUNTID, "=", accountId)
                )
        );

        where.addStatement(ITransactionEntity.TRANSDATE, "<=", isoDate);
        where.addStatement(ITransactionEntity.STATUS, "<>", TransactionStatuses.VOID.getCode());

        String selection = where.getWhere();

        AccountTransactionRepository repo = new AccountTransactionRepository(getContext());

        Cursor cursor = getContext().getContentResolver().query(repo.getUri(),
                null,
                selection,
                null,
                null);
        if (cursor == null) return total;

        AccountTransactionDisplay tx = new AccountTransactionDisplay();
        Money amount;

        // calculate balance.
        while (cursor.moveToNext()) {
            tx.contentValues.clear();
            String transType = cursor.getString(cursor.getColumnIndex(ITransactionEntity.TRANSCODE));

            // Some users have invalid Transaction Type. Should we check .contains()?

            switch (TransactionTypes.valueOf(transType)) {
                case Withdrawal:
                    DatabaseUtils.cursorDoubleToContentValues(cursor, ITransactionEntity.TRANSAMOUNT,
                            tx.contentValues, QueryAllData.Amount);
                    amount = tx.getAmount();
                    total = total.subtract(amount);
                    break;
                case Deposit:
                    DatabaseUtils.cursorDoubleToContentValues(cursor, ITransactionEntity.TRANSAMOUNT,
                            tx.contentValues, QueryAllData.Amount);
                    amount = tx.getAmount();
                    total = total.add(amount);
                    break;
                case Transfer:
                    DatabaseUtils.cursorDoubleToContentValues(cursor, ITransactionEntity.ACCOUNTID,
                            tx.contentValues, QueryAllData.ACCOUNTID);

                    if (tx.getAccountId().equals(accountId)) {
                        DatabaseUtils.cursorDoubleToContentValues(cursor, ITransactionEntity.TRANSAMOUNT,
                                tx.contentValues, QueryAllData.Amount);
                        amount = tx.getAmount();
                        total = total.subtract(amount);
                    } else {
                        DatabaseUtils.cursorDoubleToContentValues(cursor, ITransactionEntity.TOTRANSAMOUNT,
                                tx.contentValues, QueryAllData.Amount);
                        amount = tx.getAmount();
                        total = total.add(amount);
                    }
                    break;
            }
        }

        cursor.close();
        return total;
    }

    public String getAccountCurrencyCode(int accountId) {
        AccountRepository repo = new AccountRepository(getContext());
        Account account = (Account) repo.first(Account.class,
                new String[] {Account.CURRENCYID},
                Account.ACCOUNTID + "=?",
                new String[] { Integer.toString(accountId)},
                null);
        int currencyId = account.getCurrencyId();

        CurrencyService currencyService = new CurrencyService(getContext());
        return currencyService.getCurrency(currencyId).getCode();
    }

    public Cursor getCursor(boolean open, boolean favorite, List<String> accountTypes) {
        try {
            return getCursorInternal(open, favorite, accountTypes);
        } catch (Exception ex) {
            Timber.e(ex, "getting cursor in account repository");
        }
        return null;
    }

    public List<String> getTransactionAccountTypeNames() {
        List<String> accountTypeNames = new ArrayList<>();
        List<AccountTypes> accountTypes = getTransactionAccountTypes();

        for (AccountTypes type : accountTypes) {
            accountTypeNames.add(type.toString());
        }

        return accountTypeNames;
    }

    public List<AccountTypes> getTransactionAccountTypes() {
        List<AccountTypes> list = new ArrayList<>();

        list.add(AccountTypes.CASH);
        list.add(AccountTypes.CHECKING);
        list.add(AccountTypes.TERM);
        list.add(AccountTypes.CREDIT_CARD);
        list.add(AccountTypes.LOAN);
        list.add(AccountTypes.SHARES);

        return list;
    }

    public List<Account> getTransactionAccounts(boolean openOnly, boolean favoriteOnly) {
        List<String> accountTypeNames = getTransactionAccountTypeNames();

        List<Account> result = loadAccounts(openOnly, favoriteOnly, accountTypeNames);

        return result;
    }

    *//**
     * Check if the account isValidID used in any of the transactions.
     * @param accountId id of the account
     * @return a boolean indicating if there are any transactions using this account.
     *//*
    public boolean isAccountUsed(int accountId) {
        WhereStatementGenerator where = new WhereStatementGenerator();
        // transactional accounts
        where.addStatement(
                where.concatenateOr(
                        where.getStatement(ITransactionEntity.ACCOUNTID, "=", accountId),
                        where.getStatement(ITransactionEntity.TOACCOUNTID, "=", accountId)
                )
        );

        AccountTransactionRepository repo = new AccountTransactionRepository(getContext());
        int txCount = repo.count(where.getWhere(), null);

        // investment accounts
        StockRepository stockRepository = new StockRepository(getContext());
        where.clear();
        where.addStatement(StockFields.HELDAT, "=", accountId);
        int investmentCount = stockRepository.count(where.getWhere(), null);

        return (txCount + investmentCount) > 0;
    }

    public void loadTransactionAccountsToSpinner(Spinner spinner) {
        if (spinner == null) return;

        LookAndFeelSettings settings = new AppSettings(getContext()).getLookAndFeelSettings();

        Cursor cursor = this.getCursor(settings.getViewOpenAccounts(),
                settings.getViewFavouriteAccounts(), this.getTransactionAccountTypeNames());

        int[] adapterRowViews = new int[] { android.R.id.text1 };

        ToolbarSpinnerAdapter cursorAdapter = new ToolbarSpinnerAdapter(getContext(),
                android.R.layout.simple_spinner_item,
                cursor,
                new String[] { Account.ACCOUNTNAME, Account.ACCOUNTID },
                adapterRowViews,
                SimpleCursorAdapter.NO_SELECTION);
//        cursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cursorAdapter.setDropDownViewResource(R.layout.toolbar_spinner_item_dropdown);

        spinner.setAdapter(cursorAdapter);
    }

    public void loadInvestmentAccountsToSpinner(Spinner spinner, boolean showAllAccountsItem) {
        if (spinner == null) return;

        AccountRepository repo = new AccountRepository(getContext());
        Cursor cursor = repo.getInvestmentAccountsCursor(true);
        Cursor finalCursor = cursor;

        if (showAllAccountsItem) {
            // append All Accounts item
            MatrixCursor extras = new MatrixCursor(new String[]{"_id", Account.ACCOUNTID,
                    Account.ACCOUNTNAME, Account.INITIALBAL});
            String title = getContext().getString(R.string.all_accounts);
            extras.addRow(new String[]{Integer.toString(Constants.NOT_SET),
                    Integer.toString(Constants.NOT_SET), title, "0.0"});
            Cursor[] cursors = {extras, cursor};
            finalCursor = new MergeCursor(cursors);
        }

        int[] adapterRowViews = new int[] { android.R.id.text1 };

        ToolbarSpinnerAdapter cursorAdapter = new ToolbarSpinnerAdapter(getContext(),
                android.R.layout.simple_spinner_item,
                finalCursor,
                new String[] { Account.ACCOUNTNAME, Account.ACCOUNTID },
                adapterRowViews,
                SimpleCursorAdapter.NO_SELECTION);
        cursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(cursorAdapter);
    }

    public List<Account> loadAccounts(boolean openOnly, boolean favoriteOnly, List<String> accountTypes) {
        List<Account> result = new ArrayList<>();

        Cursor cursor = getCursor(openOnly, favoriteOnly, accountTypes);
        if (cursor == null) {
            new UIHelper(getContext()).showToast("Error reading accounts list!");
            return result;
        }

        while (cursor.moveToNext()) {
            Account account = new Account();
            account.loadFromCursor(cursor);
            result.add(account);
        }
        cursor.close();

        return result;
    }

    public Money loadInitialBalance(int accountId) {
        AccountRepository repo = new AccountRepository(getContext());
        Account account = repo.load(accountId);
        return account.getInitialBalance();
    }

    *//**
     * Loads account details with balances.
     * Needs to be better organized to limit the where clause.
     * @param where selection criteria for Select Account Bills
     * @return current balance in the currency of the account.
     *//*
    public Money loadBalance(String where) {
        Money curTotal = MoneyFactory.fromString("0");

        QueryAccountBills accountBills = new QueryAccountBills(getContext());
        Cursor cursor = getContext().getContentResolver().query(accountBills.getUri(),
                null,
                where,
                null,
                null);
        if (cursor == null) return curTotal;

        // calculate summary
        while (cursor.moveToNext()) {
//            curTotal = curTotal.add(MoneyFactory.fromDouble(cursor.getDouble(cursor.getColumnIndex(QueryAccountBills.TOTAL))));
            curTotal = curTotal.add(MoneyFactory.fromString(cursor.getString(cursor.getColumnIndex(QueryAccountBills.TOTAL))));
        }
        cursor.close();

        return curTotal;
    }

    // Private

    private Cursor getCursorInternal(boolean openOnly, boolean favoriteOnly, List<String> accountTypes) {
        AccountRepository repo = new AccountRepository(getContext());

        String where = getWhereFilterFor(openOnly, favoriteOnly);

        if (accountTypes != null && accountTypes.size() > 0) {
            where = DatabaseUtils.concatenateWhere(where, getWherePartFor(accountTypes));
        }

        Cursor cursor = getContext().getContentResolver().query(repo.getUri(),
                repo.getAllColumns(),
                where,
                null,
                "lower (" + Account.ACCOUNTNAME + ")"
        );
        return cursor;
    }

    private String getWhereFilterFor(boolean openOnly, boolean favoriteOnly) {
        StringBuilder where = new StringBuilder();

        if (openOnly) {
            where.append("LOWER(STATUS)='open'");
        }
        if (favoriteOnly) {
            if (openOnly) {
                where.append(" AND ");
            }
            where.append("LOWER(FAVORITEACCT)='true'");
        }

        return where.toString();
    }

    private String getWherePartFor(List<String> accountTypes) {
        StringBuilder where = new StringBuilder();
        where.append(Account.ACCOUNTTYPE);
        where.append(" IN (");
        for(String type : accountTypes) {
            if (accountTypes.indexOf(type) > 0) {
                // if not first, add comma before the type name
                where.append(',');
            }

            where.append("'");
            where.append(type);
            where.append("'");
        }
        where.append(")");

        return where.toString();
    }*/
}

