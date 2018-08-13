package club.ensoul.labs.daily.test.KMP算法;

public class KMP {
    public static int kmp(String str, String dest, int[] next) {//str文本串  dest 模式串
        for(int i = 0, j = 0; i < str.length(); i++) {
            while(j > 0 && str.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if(str.charAt(i) == dest.charAt(j)) {
                j++;
            }
            if(j == dest.length()) {
                return i - j + 1;
            }
        }
        return 0;
    }
    
    public static int[] kmpnext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for(int i = 1, j = 0; i < dest.length(); i++) {
            while(j > 0 && dest.charAt(j) != dest.charAt(i)) {
                j = next[j - 1];
            }
            if(dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    
    public static void main(String[] args) {
        String a = "ababa";
        String b = "ssdfgasdbababa";
        int[] next = kmpnext(a);
        int res = kmp(b, a, next);
        System.out.println("res:" + res);
        for(int i = 0; i < next.length; i++) {
            System.out.print(next[i] + " ");
        }
        System.out.println();
        System.out.println("length:" + next.length);
    
        String s1 = new StringBuilder("ja").append("va").toString();
        System.out.println(s1.intern() == s1);
        String s2 = new StringBuilder("ssss").append("ll").toString();
        System.out.println(s2.intern() == s2);
        
    }
}