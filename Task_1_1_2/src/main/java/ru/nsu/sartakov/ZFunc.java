package ru.nsu.sartakov;

public class ZFunc {
    public static StringBuilder search(int shift, String text, String pattern) {
        StringBuilder res = new StringBuilder();
        String concat = pattern + "$" + text;
        int[] Z = new int[concat.length()];
        getZarr(concat, Z);
        for(int i = 0; i < concat.length(); ++i){
            if(Z[i] == pattern.length()){
                res.append(shift + i - pattern.length() - 1).append(" ");
            }
        }
        return res;
    }
    private static void getZarr(String str, int[] Z) {
        int n = str.length();
        int L = 0, R = 0;
        for(int i = 1; i < n; ++i) {
            if(i > R){
                L = R = i;
                while(R < n && str.charAt(R - L) == str.charAt(R))
                    R++;
                Z[i] = R - L;
                R--;
            }
            else{
                int k = i - L;
                if(Z[k] < R - i + 1)
                    Z[i] = Z[k];
                else{
                    L = i;
                    while(R < n && str.charAt(R - L) == str.charAt(R))
                        R++;
                    Z[i] = R - L;
                    R--;
                }
            }
        }
    }
}
