package ru.nsu.sartakov;

public class ZFunc {
    public static int search(int shift, String text, String pattern) {
        int res = 0;
        String concat = pattern + "$" + text;
        int[] Z = new int[concat.length()];
        getZarr(concat, Z);
        for(int i = 0; i < concat.length(); ++i){
            if(Z[i] == pattern.length()){
                System.out.print((shift + i - pattern.length() - 1) + " ");
                res += 1;
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
