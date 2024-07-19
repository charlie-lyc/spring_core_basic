package hello.core.singleton;

/**
 * 싱글턴 방식의 주의점
 * - 하나의 동일한 객체 인스턴스를 공유하기 때문에 stateful 하면 안되며,
 * - 반드시 stateless 하게 설계해야 한다!
 * - 특정 클라이언트에 의존적이면 안되고, 값을 변경할 수 있는 필드는 없어야 하며,
 * - 읽기만 가능해야 한다!
 * - 특히 스프링 빈의 필드에 공유 값을 설정하면 절대 안되며,
 * - 필드 대신 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다!
 */

/**
 * 상태를 유지할 경우 발생하는 문제점
 */
public class StatefulService {

    private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("Name: " + name + ", Price: " + price);
        this.price = price; // 여기가 문제!
    }

    public int getPrice() {
        return price;
    }
}