package hello.core.singleton;

/**
 * Singleton Pattern : 클래스의 인스턴스가 1개만 생성되는 것을 보장하는 디자인 패턴
 * - 객체 인스턴스가 2개 이상 생성되지 않도록 막음
 * - 생성자 앞에 'private' 을 붙여서 외부에서 임의로 new 키워드를 사용하지 못하도록 막음
 */
public class SingletonService {

    // 1. static 영역에 객체를 딱 1개만 생성해 둔다.
    private static final SingletonService instance = new SingletonService();

    // 2. public 으로 열어서 객체 인스턴스가 필요하면 이 static method 를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private 으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {}

    public void logic() {
        System.out.println("싱글턴 객체 로직 호출");
    }
}
