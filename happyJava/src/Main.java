interface StorageService {

  void save(String data);
}

class FileStorage implements StorageService {

  @Override
  public void save(String data) {
    System.out.println("데이터를 파일에 저장합니다. " + data);
  }
}

class DatabaseStorage implements StorageService {

  @Override
  public void save(String data) {
    System.out.println("데이터를 DB에 저장합니다. " + data);
  }
}

class DataManager {

  private final StorageService storageService;

  public DataManager(StorageService storageService) {
    this.storageService = storageService;
  }

  public void saveData(String data) {
    storageService.save(data);
  }
}

public class Main {

  public static void main(String[] args) {

//        DataManager dataManager = new DataManager();
//        dataManager.saveData("실습데이터입니다.");

    //DIP 가 적용된 후에는 아래 코드로 실행될 수 있도록 코드를 수정해 주세요.

    // 파일 저장 방식
    StorageService fileStorage = new FileStorage();
    DataManager fileDataManager = new DataManager(fileStorage);
    fileDataManager.saveData("파일 저장 데이터");

    // 데이터베이스 저장 방식
    StorageService dbStorage = new DatabaseStorage();
    DataManager dbDataManager = new DataManager(dbStorage);
    dbDataManager.saveData("데이터베이스 저장 데이터");
  }
}