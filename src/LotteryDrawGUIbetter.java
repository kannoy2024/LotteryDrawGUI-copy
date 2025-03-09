import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.*;

public class LotteryDrawGUIbetter extends JFrame {
    private static final int MAX_NUMBER = 36; // 随机数的范围应为37
    private String[] MonsterList = {
        "麒麟",
        "钢龙",
        "炎妃龙",
        "炎王龙",
        "灭尽龙",
        "轰龙",
        "冰牙龙",
        "惶怒恐暴龙",
        "碎龙",
        "斩龙",
        "硫斩龙",
        "歼世灭尽龙",
        "凶爪龙",
        "雾瘴尸套龙",
        "红莲爆鳞龙",
        "溟波龙",
        "天地煌啼龙",
        "煌黑龙",
        "金火龙",
        "银火龙",
        "黑狼鸟",
        "金狮子",
        "激昂金狮子",
        "黑轰龙",
        "雷狼龙",
        "狱狼龙",
        "猛爆碎龙",
        "战痕黑狼鸟",
        "霜刃冰牙龙",
        "黑龙",
        "冰呪龙",
        "贝希摩斯",
        "古代鹿首精",
        "迅龙",
        "雷颚龙",
        "水妖鸟",
        "霜翼风漂龙"
    };
    private static Set<Integer> drawnNumbers = new HashSet<>();
    private JTextArea drawnNumbersTextArea;
    private Random random;

    public LotteryDrawGUIbetter() { 
        setTitle("黑龙性奴小队抽奖器"); // 修改为更合适的标题
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        random = new Random();

        // 创建按钮
        JButton drawButton = new JButton("开！");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawNumber();
            }
        });

        // 创建文本区域
        drawnNumbersTextArea = new JTextArea();
        drawnNumbersTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(drawnNumbersTextArea); 

        // 创建面板并添加组件
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(drawButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private void drawNumber() {
        if (drawnNumbers.size() >= MAX_NUMBER) {
            JOptionPane.showMessageDialog(this, "所有数字已被抽完!", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int newNumber;
        do {
            newNumber = random.nextInt(MAX_NUMBER);
        } while (drawnNumbers.contains(newNumber));
        drawnNumbers.add(newNumber);

        // 更新文本区域
        String name = MonsterList[newNumber];
        drawnNumbersTextArea.append("今天我们打: " + name + "\n");
        drawnNumbersTextArea.append("已经生成的数字: " + drawnNumbers + "\n\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LotteryDrawGUIbetter().setVisible(true);
            }
        });
    }
}