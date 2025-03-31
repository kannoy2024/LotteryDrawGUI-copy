import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LotteryDrawGUIbetter extends JFrame {
    private JTextArea drawnNumbersTextArea;
    private JTextField monsterNameTextField;
    private JTextField deleteMonsterNameTextField;
    private MonsterManager monsterManager;
    private LotteryLogic lotteryLogic;
//：负责GUI界面的创建和事件处理。它与MonsterManager和LotteryLogic类交互，处理用户输入和显示结果。
    public LotteryDrawGUIbetter() { 
        setTitle("黑龙小队抽奖器"); // 修改为更合适的标题
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        monsterManager = new MonsterManager();
        lotteryLogic = new LotteryLogic(monsterManager.getMonsterList());

        // 创建按钮
        JButton drawButton = new JButton("开！");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawNumber();
            }
        });

        // 创建输入框和保存按钮
        monsterNameTextField = new JTextField(20);
        JButton saveButton = new JButton("添加怪物");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMonsterNameToFile();
            }
        });

        // 创建删除输入框和删除按钮
        deleteMonsterNameTextField = new JTextField(20);
        JButton deleteButton = new JButton("删除怪物");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMonsterNameFromFile();
            }
        });

        // 创建文本区域
        drawnNumbersTextArea = new JTextArea();
        drawnNumbersTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(drawnNumbersTextArea); 

        // 创建面板并添加组件
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 抽奖按钮面板
        JPanel drawButtonPanel = new JPanel();
        drawButtonPanel.add(drawButton);
        mainPanel.add(drawButtonPanel, BorderLayout.NORTH);

        // 输入框和保存按钮面板
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("添加怪物名字: "));
        inputPanel.add(monsterNameTextField);
        inputPanel.add(saveButton);

        // 删除输入框和删除按钮面板
        JPanel deletePanel = new JPanel();
        deletePanel.add(new JLabel("删除怪物名字: "));
        deletePanel.add(deleteMonsterNameTextField);
        deletePanel.add(deleteButton);

        // 将输入框和删除框面板添加到SOUTH位置
        JPanel southPanel = new JPanel(new GridLayout(2, 1));
        southPanel.add(inputPanel);
        southPanel.add(deletePanel);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void drawNumber() {
        if (lotteryLogic.isAllNumbersDrawn()) {
            JOptionPane.showMessageDialog(this, "所有数字已被抽完!", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = lotteryLogic.drawNumber();
        drawnNumbersTextArea.append("今天我们打: " + name + "\n");
        drawnNumbersTextArea.append("已经生成的数字: " + lotteryLogic.getDrawnNumbers() + "\n\n");
    }

    private void saveMonsterNameToFile() {
        String newMonsterName = monsterNameTextField.getText().trim();
        if (newMonsterName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入怪物名字", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (monsterManager.saveMonsterName(newMonsterName)) {
            // 清空输入框
            monsterNameTextField.setText("");

            // 重新读取文件内容到MonsterList
            lotteryLogic.updateMonsterList(monsterManager.getMonsterList());

            JOptionPane.showMessageDialog(this, "怪物名字已保存", "成功", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "保存文件失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteMonsterNameFromFile() {
        String monsterToDelete = deleteMonsterNameTextField.getText().trim();
        if (monsterToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入要删除的怪物名字", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (monsterManager.deleteMonsterName(monsterToDelete)) {
            // 清空输入框
            deleteMonsterNameTextField.setText("");

            // 重新读取文件内容到MonsterList
            lotteryLogic.updateMonsterList(monsterManager.getMonsterList());

            JOptionPane.showMessageDialog(this, "怪物名字已删除", "成功", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "删除文件失败或怪物名字未找到", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }


}