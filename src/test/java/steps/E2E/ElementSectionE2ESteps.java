package steps.E2E;

import com.thoughtworks.gauge.*;
import commonUtils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import pages.ElementSectionObject;

public class ElementSectionE2ESteps {
    private WebDriver driver;
    private ElementSectionObject elementSectionObject;

    @BeforeScenario
    public void setUp(ExecutionContext context) {
        // Check if the scenario is tagged as "api"
        if (context.getCurrentScenario().getTags().contains("Api")) {
            return;
        }

        // Initialize WebDriver only for UI tests
        this.driver = DriverFactory.getDriver();
        this.elementSectionObject = new ElementSectionObject(driver);
    }

    @Step("Navigate to DEMOQA page")
    public void navigateToDemoQA() {
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
    }

    @Step("Verify page title <expectedTitle>")
    public void verifyPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Page title does not match expected title", actualTitle, expectedTitle);
    }

    @Step("Access <Elements> section")
    public void accessDemoQASections(String sectionName) {
        elementSectionObject.demoQASections(sectionName).click();
        }

    @Step("Close browser")
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Verify that I'm in <expectedSection> section")
    public void verifySection(String expectedSection) {
        elementSectionObject.verifySectionByUrl(expectedSection);
    }

    @Step("Click on <textOption> option")
    public void clickOnSideMenuOption(String textOption) {
        elementSectionObject.getSideMenuOptionLocator(textOption).click();
    }


    @Step("Fill in full name <Name>")
    public void fillInFullName(String name) {
        elementSectionObject.fillFullName(name);
    }

    @Step("Fill in email <Email>")
    public void fillInEmail(String email) {
        elementSectionObject.fillFullEmail(email);
    }

    @Step("Fill in current address <Address>")
    public void fillCurrentAddress(String address) {
        elementSectionObject.fillCurrentAddress(address);
    }

    @Step("Fill in permanent address <Address>")
    public void fillPermanentAddress(String address) {
        elementSectionObject.fillPermanentAddress(address);
    }

    @Step("Click submit button and verify form submission is successful")
    public void clickSubmitButton() {
        elementSectionObject.submitSurvey();
        Assert.assertTrue("The submit went wrong!", elementSectionObject.surveyElementsPresent());
    }

    @Step("Verify submitted name matches <Name>")
    public void surveyNameSubmittedVerification(String name) {
        Assert.assertTrue("Submitted name does not match the expected name", elementSectionObject.surveySubmittedNameComparison(name));
    }

    @Step("Verify submitted email matches <email>")
    public void surveyEmailSubmittedVerification(String email) {
        Assert.assertTrue("Submitted Email does not match the expected Email", elementSectionObject.surveySubmittedEmailComparison(email));
    }

    @Step("Expand all check box options")
    public void expandAllCheckBoxOptions() {
        elementSectionObject.expandCheckboxArrowByLabel("home").click();
    }

    @Step("Select <Documents> checkbox")
    public void selectDocumentsCheckbox(String checkbox) {
        elementSectionObject.desiredCheckBox(checkbox).click();
    }

    @Step("Verify <Documents> folder is selected")
    public void verifyDocumentsFolderSelected(String checkboxText) {
        Assert.assertTrue("Documents checkbox is not selected", elementSectionObject.isElementSelected(checkboxText));
    }

    @Step("Verify that the sub-items <WorkSpace> and <office> under <documents> are selected")
    public void verifyAllSubItemsSelected(String subItem1, String subItem2, String checkboxText) {
        elementSectionObject.expandCheckboxArrowByLabel(checkboxText).click();
        Assert.assertTrue("Not all sub-items under 'Documents' are selected", elementSectionObject.areElementsSelected(subItem1, subItem2, checkboxText));
    }

    @Step("Deselect <Documents> checkbox")
    public void deselectDocumentsCheckbox(String checkbox) {
        elementSectionObject.desiredCheckBox(checkbox).click();
    }

    @Step("Verify no items are selected")
    public void verifyNoItemsSelected() {
        Assert.assertFalse("Some checkboxes are still selected", elementSectionObject.noItemsSelected());
    }

    @Step("Select the <radioButton> radio button")
    public void selectRadioButton(String radioButton) {
        elementSectionObject.clickRadioButton(radioButton);
    }

    @Step("Verify that the <radioButton> radio button is <action>")
    public void verifyRadioButtonState(String radioButton, String action) {
        boolean result = false;

        switch (action.toLowerCase()) {
            case "enabled":
                result = elementSectionObject.getRadioButtonSelector(radioButton).isEnabled();
                Assert.assertTrue(radioButton + " radio button should be enabled", result);
                break;

            case "selected":
                result = elementSectionObject.getRadioButtonSelector(radioButton).isSelected();
                Assert.assertTrue(radioButton + " radio button should be selected", result);
                break;

            case "disabled":

                result = elementSectionObject.getRadioButtonSelector(radioButton).getAttribute("class").contains("disabled");
                Assert.assertTrue(radioButton + " radio button should be disabled", result);
                break;

            default:
                throw new IllegalArgumentException("Invalid action: " + action + ". Use 'enabled', 'selected', or 'disabled'.");
        }
    }


    @Step("Add new record and verify in table the following details: <table>")
    public void addNewRecordWithDetails(Table table) {
        for (TableRow row : table.getTableRows()) {

            elementSectionObject.getAddNewTableRecord().click();

            String firstName = row.getCell("First Name");
            String lastName = row.getCell("Last Name");
            String email = row.getCell("Email");
            String age = row.getCell("Age");
            String salary = row.getCell("Salary");
            String department = row.getCell("Department");

            elementSectionObject.getInputFieldById("firstName").sendKeys(firstName);
            elementSectionObject.getInputFieldById("lastName").sendKeys(lastName);
            elementSectionObject.getInputFieldById("userEmail").sendKeys(email);
            elementSectionObject.getInputFieldById("age").sendKeys(age);
            elementSectionObject.getInputFieldById("salary").sendKeys(salary);
            elementSectionObject.getInputFieldById("department").sendKeys(department);

            elementSectionObject.getInputFieldById("submit").click();

            // Verify that the record was added
            Assert.assertTrue("Record not found in the table", elementSectionObject.verifyRecordInTable(firstName, lastName, email, age, salary, department));
        }
    }

    @Step("Search for <name>")
    public void searchForRecord(String name) {
        Assert.assertTrue("Person not found in the table after search", elementSectionObject.searchPersonInTable(name));
    }

    @Step("Edit <name> record with new salary <newSalary>")
    public void editRecordSalary(String name, String newSalary) {
        elementSectionObject.getEditPersonButton(name).click();
        elementSectionObject.getInputFieldById("salary").clear();
        elementSectionObject.getInputFieldById("salary").sendKeys(newSalary);
        elementSectionObject.getInputFieldById("submit").click();
    }

    @Step("Ensure <name>'s <infoToRetrieve> to be <expectedInfoValue>")
    public void verifyUpdatedSalary(String name, String infoToRetrieve, String expectedInfoValue) {
        String actualInfo = elementSectionObject.getInfoFromPersonInTable(name, infoToRetrieve);

        Assert.assertEquals("The " + infoToRetrieve + " expected for " + name + " was " + expectedInfoValue + ", but found: " + actualInfo, expectedInfoValue, actualInfo);
    }

    @Step("Delete <name> record")
    public void deleteRecord(String name) {
        elementSectionObject.getDeletePersonButton(name).click();
    }

    @Step("Verify record for <name> was deleted")
    public void verifyRecordDeleted(String name) {
        boolean isNotPresent = elementSectionObject.isRecordDeleted(name);
        Assert.assertTrue("Record for " + name + " should be deleted but still exists", isNotPresent);
    }

    @Step("Realize <Click> to Button")
    public void realizeClickActions(String clickType) {

        switch (clickType.toLowerCase()) {
            case "click":
                elementSectionObject.getClickButtonsByType(clickType).click();
                break;
            case "double click":
                elementSectionObject.getClickButtonsByType(clickType).doubleclick();
                break;
            case "right click":
                elementSectionObject.getClickButtonsByType(clickType).rightClick();
                break;
            default:
                throw new IllegalArgumentException("Invalid click type: " + clickType + ". Use 'click', 'double click', or 'right click'.");
        }
    }

    @Step("Verify that the message <You have clicked the button> is displayed")
    public void implementation1(String message) {
        Assert.assertTrue("The success message after the click action doesn't have appeared.", elementSectionObject.getButtonMessageAfterClick(message).isDisplayed());
    }
}

