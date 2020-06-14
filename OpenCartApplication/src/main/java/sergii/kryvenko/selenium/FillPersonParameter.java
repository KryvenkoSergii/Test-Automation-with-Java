package sergii.kryvenko.selenium;

public class FillPersonParameter {

    public static void main(String[] args) {

        Person person = new Person();
        
        String firstName = "John";
        String lastName = "Shepard";
        String company = "freelancer";
        String address1 = "USA";
        String address2 = "England";
        String city = "London";
        String postcode = "123456";
        String country = "United Kingdom";
        String region = "Cambridgeshire";
        String commentInOrder = "Comments About Your Order";
        
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setCompany(company);
        person.setAddress1(address1);
        person.setAddress2(address2);
        person.setCity(city);
        person.setPostcode(postcode);
        person.setCountry(country);
        person.setRegion(region);
        person.setCommentInOrder(commentInOrder);
    }

}
