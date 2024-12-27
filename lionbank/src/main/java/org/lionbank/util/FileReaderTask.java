package org.lionbank.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileReaderTask extends Thread {
  private String logMessage;  // 로그 메시지
  private static final String LOG_FILE = "log.txt";  // 로그 파일 경로

  // 생성자, 로그 메시지를 받아서 설정
  public FileReaderTask(String logMessage) {
    this.logMessage = logMessage;
  }

  // 쓰레드 실행 시 로그를 파일에 기록
  @Override
  public void run() {
    writeLogToFile(logMessage);
  }

  // 로그를 파일에 작성하는 메서드
  private void writeLogToFile(String message) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
      // 현재 시간 얻기
      String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      // 로그 내용 작성
      writer.write("[" + timestamp + "] " + message);
      writer.newLine();
    } catch (IOException e) {
      System.out.println("로그 파일을 작성하는 중 오류가 발생했습니다: " + e.getMessage());
    }
  }
}
