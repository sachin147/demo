import spock.lang.Specification

class OrderMakerSpecification extends  Specification {
    OrderMaker orderMaker
    void setup() {
        orderMaker = new OrderMaker()
    }

    def "should return a final price after removing ingredients"(String order, Double result) {
        expect:
        orderMaker.placeOrder(order) == result

        where:
        order | result
        "Chai, -sugar" | 3.5
        "Chai, -sugar, -milk" | 2.5
        "Chai" | 4.0
    }

    def "should throws InvalidOrderException if invalid order placed"() {
        given:
        String order = "Unknown"

        when:
        orderMaker.placeOrder(order)

        then:
        thrown InvalidOrderException
    }

    def "should throws InvalidOrderException if invalid ingredient removed"() {
        given:
        String order = "Chai, -invalidingredient"

        when:
        orderMaker.placeOrder(order)

        then:
        thrown InvalidOrderException
    }

    def "should throws InvalidOrderException if all  ingredients removed"() {
        given:
        String order = "Chai, -tea, -milk, -sugar, -water"

        when:
        orderMaker.placeOrder(order)

        then:
        thrown InvalidOrderException
    }
}
