/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.frcsimulator;

import java.util.*;

/**
 *
 * @author bpylko2015
 */
public class Arguments {

    protected Properties argProperties = new Properties();

    public Arguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                ArrayList<String> temp = new ArrayList();
                String argName = args[i].substring(1);
                if (argName.startsWith("-")) {
                    argName = argName.substring(1);
                }
                i++;
                for (; i < args.length && !args[i].startsWith("-"); i++) {
                    temp.add(args[i]);
                }
                i--;
                argProperties.put(argName, temp.toArray(new String[temp.size()]));
            }
        }
    }

    public String[] get(String arg) {
        return (String[]) argProperties.get(arg);
    }
}
