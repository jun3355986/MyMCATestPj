import com.jun.arithmetic.PrintBinary;
import com.jun.common.innerclass.Outer;
import lombok.extern.slf4j.Slf4j;

/**
 * @className: Lab
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/3/22 00:02
 **/
@Slf4j
public class Lab {

    public static void main(String[] args){
        PrintBinary.printBinary(54);

        log.info("-----------------------------------------------------");
        log.info("最大整数： {}", Integer.MAX_VALUE);
        PrintBinary.printBinary(Integer.MAX_VALUE);
        PrintBinary.printBinary(Integer.MIN_VALUE);
        log.info("最小整数： {}", Integer.MIN_VALUE);
        log.info("最小整数左移2位： {}", Integer.MIN_VALUE - 10 << 2);


    }
}
