import com.jun.arithmetic.Dichotomy;
import com.jun.patterm.singleton.Mgr04;
import com.jun.patterm.singleton.Mgr05;
import com.jun.patterm.singleton.Mgr07;
import com.jun.patterm.singleton.Mgr08;
import com.jun.patterm.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @className: TestLab
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/3/24 08:35
 **/
@Slf4j
public class TestLab {

    @Test
    public void dichotomy() {
        int[] arr = {1,3, 4, 5,8, 10, 12, 16, 18, 19, 20, 24, 25, 25};
        int num = 17;
        Dichotomy.genericFind(arr, num);
        Dichotomy.find(arr, num);
    }

    @Test
    public void testMostLeftNoLessNumIndex() {
        int[] arr = {1,3, 4, 5,8, 10, 12, 16, 18, 19, 20, 24, 25, 25};
        int num = 17;
        Dichotomy.mostLeftNoLessNumIndex(arr, num);
    }

    @Test
    public void testSingleton() {
        for (int i =0 ; i < 100; i++) {
            new Thread(() -> log.info("实例：{}", Mgr08.INSTANCE.hashCode())).start();
        }

    }

    @Test
    public void testStrategy() {
        ZhaZha zhaZha = new ZhaZha(new CannotDoNotThing(), new SpeakChinese());
        zhaZha.iCanDo();
        zhaZha.iCanSpeak();
        Talent Talent = new Talent(new CanDoAllThing(), new SpeakEnglish());
        Talent.iCanDo();
        Talent.iCanSpeak();
    }

    @Test
    public void testOneMinIndex() {
        int maxLen = 30;
        int maxValue = 100;
        int testTime = 1000000;
        log.info("开始测试");
        int mark = 10;
        for(int i = 0; i < testTime; i++ ) {
            int[] arr = Dichotomy.randomArray(maxLen, maxValue);
            int ans = Dichotomy.oneMinIndex(arr);
            if (!Dichotomy.check(arr, ans)) {
                log.info("数组：{}", arr);
                log.info("出问题的地方：{}", ans);
                break;
            }
            if (i == mark) {
                log.info("数组：{}", arr);
                log.info("局部最小位置：{}", ans);
            }
        }
        log.info("开始结束");

    }

}
