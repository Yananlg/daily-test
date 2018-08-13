package club.ensoul.labs.daily.test.中缀表达式转后缀表达式;

import java.util.Stack;

/**
 * 中缀表达式转后缀表达式
 * 我们把平时所用的标准四则运算表达式，即“9+(3-1)×3+10÷2”叫做中缀表达式。因为所有的运算符号都在两数字的中间，现在我们的问题就是中缀到后缀的转化。
 * 中缀表达式“9+(3-1)×3+10÷2”转化为后缀表达式“9 3 1-3*+10 2/+”。
 * 规则：
 *      从左到右遍历中缀表达式的每个数字和符号，若是数字就输出，即成为后缀表达式的一部分；
 *      若是符号，则判断其与栈顶符号的优先级，是右括号或优先级不高于栈顶符号（乘除优先加减）则栈顶元素依次出栈并输出，并将当前符号进栈，一直到最终输出后缀表达式为止。
 */
public class RPN {
    
    public static void main(String[] args) {
        String str = "9+(3-1)*3+10/2";
        Stack<String> stack = new Stack<>();
        
        StringBuilder builder = new StringBuilder(str.length() * 2);
        
        StringBuilder ss = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            if(s.matches("[0-9]")) {
                ss.append(String.valueOf(str.charAt(i)));
                if(i == str.length() - 1) {
                    builder.append(ss).append(" ");
                    ss = new StringBuilder();
                }
            } else {
                if(!ss.toString().equals("")) {
                    builder.append(ss).append(" ");
                    ss = new StringBuilder();
                }
                if(s.matches("[\\+\\-\\*\\/\\(\\)]")) {
                    if(stack.isEmpty()) {
                        stack.push(s);
                    } else if(s.matches("[\\*\\/\\(]")) {
                        stack.push(s);
                    } else if(s.matches("[\\+\\-]") && stack.peek().matches("[\\*\\/]")) {
                        while(!stack.isEmpty()) {
                            builder.append(stack.pop()).append(" ");
                        }
                        stack.push(s);
                    } else if(s.matches("\\)")) {
                        while(!stack.peek().equals("(")) {
                            builder.append(stack.pop()).append(" ");
                        }
                        stack.pop();    // 移除左括号
                    } else {
                        stack.push(s);
                    }
                } else {
                    System.out.println("str error");
                }
            }
        }
        
        // 最终栈数据
        while(!stack.isEmpty()) {
            builder.append(stack.pop()).append(" ");
        }
        
        System.out.println(builder);
    }
    
    
}
