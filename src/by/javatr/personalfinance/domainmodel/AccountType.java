/*
 * Copyright (C) 2012-2018 The Android Money Manager Ex Project Team
 *
 * This program isValidID free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program isValidID distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package by.javatr.personalfinance.domainmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumeration of account types.
 */
public enum AccountType {
    CASH("Cash"),
    CHECKING ("Checking"),
    INVESTMENT ("Investment"),
    TERM ("Term"),
    CREDIT_CARD ("Credit Card"),
    LOAN ("Loan"),
    SHARES ("Shares");


    public static AccountType get(String name) {
        for (AccountType type : AccountType.values()) {
            if (type.title.equals(name)) return type;
        }
        return null;
    }

    public static String[] getNames() {
        List<String> list = new ArrayList<>();

        for (AccountType type : AccountType.values()) {
            list.add(type.title);
        }

        String[] result = new String[list.size()];
        return list.toArray(result);
    }

    public final String title;

    AccountType(String s) {
        title = s;
    }

    public boolean equalsName(String otherTitle) {
        return (otherTitle == null) ? false : title.equalsIgnoreCase(otherTitle);
    }

    public String toString(){
        return this.title;
    }
}
