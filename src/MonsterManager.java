import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
//负责文件读写操作，包括读取怪物名单、保存新怪物名字和删除怪物名字。它维护一个Set<String>来存储怪物名单。
public class MonsterManager {
    private static final String FILE_PATH = "f:\\Java2025\\LotteryDrawGUI copy\\src\\目录.txt";
    private Set<String> monsterSet;

    public MonsterManager() {
        monsterSet = new HashSet<>();
        readMonsterListFromFile();
    }

    public String[] getMonsterList() {
        return monsterSet.toArray(new String[0]);
    }

    private void readMonsterListFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) { // 忽略空行
                    monsterSet.add(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean saveMonsterName(String newMonsterName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.newLine();
            bw.write(newMonsterName);
            bw.flush();
            readMonsterListFromFile(); // 重新读取文件内容
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMonsterName(String monsterToDelete) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
             BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH + ".tmp"))) {

            String line;
            boolean deleted = false;
            while ((line = br.readLine()) != null) {
                if (!line.trim().equals(monsterToDelete)) {
                    bw.write(line);
                    bw.newLine();
                } else {
                    deleted = true;
                }
            }

            if (deleted) {
                bw.flush();
                br.close();
                bw.close();
                new java.io.File(FILE_PATH).delete();
                new java.io.File(FILE_PATH + ".tmp").renameTo(new java.io.File(FILE_PATH));
                readMonsterListFromFile(); // 重新读取文件内容
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}