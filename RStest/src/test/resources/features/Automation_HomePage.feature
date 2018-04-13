Feature:Feature:Verify Five Scenarios on the RS HomePage

  @Positive

  Scenario Outline:Verifying page has been navigated to appropriate  page whilst clicking "Log In" & "Register" link

    Given I want to run in "headlessChrome"
    When I am on "<Page>"
    And I click TextLink: "<Textlink>"
    Then I am taken to "<result>"
    Examples:
      | Page                                                    | Textlink | result                     |
      | https://uk.rs-online.com/web/generalDisplay.html?id=new | Log In   | /web/authHome.html         |
      | https://uk.rs-online.com/web/generalDisplay.html?id=new | Register | /web/register/registration |

  @Positive

  Scenario Outline:Verifying all relevant headers categories  been displayed

    Given I want to run in "headlessChrome"
    When I am on "<Page>"
    Then The page should display header:"<Header>" option
    Examples:
      | Page                                                    | Header                   |
      | https://uk.rs-online.com/web/generalDisplay.html?id=new | Categories               |
      | https://uk.rs-online.com/web/generalDisplay.html?id=new | New Products             |
      | https://uk.rs-online.com/web/generalDisplay.html?id=new | Featured Brands          |
      | https://uk.rs-online.com/web/generalDisplay.html?id=new | Applications & Solutions |

  @Positive

  Scenario Outline: Verifying all navigation options are displayed
    Given I want to run in "headlessChrome"
    When I am on "<Page>"
    Then The page should display Navigation Option:"<NavigationOptions>"
    Examples:
      | Page                                                    | NavigationOptions                                            |
      | https://uk.rs-online.com/web/generalDisplay.html?id=new | All Products,Our Brands,My Account,New Products,Our Services |

  @Positive

  Scenario Outline: Verifying expected options under Categories are been displayed
    Given I want to run in "headlessChrome"
    When I am on "<Page>"
    Then The page should display Categories Option:"<CategoriesOption>"
    Examples:
      | Page                                                    | CategoriesOption                                                            |
      | https://uk.rs-online.com/web/generalDisplay.html?id=new | Abrasives & Engineering Materials,Batteries,Connectors,Semiconductors,Tools |

  @Positive
  Scenario Outline:Verifying search box by entering different item and searching application is navigating to appropriate page
    Given I want to run in "headlessChrome"
    When I am on "<Page>"
    And I search for an item:"<Location>" in search box
    Then I am taken to "<result>"
    Examples:
      | Page                                                    | Location      | result                                                           |
      | https://uk.rs-online.com/web/generalDisplay.html?id=new | 7921410000    | /web/p/connector-tool-kits/0193966/                              |
      | https://uk.rs-online.com/web/generalDisplay.html?id=new | 358-8301      | /web/p/interface-relay-modules/3588301/                          |
      | https://uk.rs-online.com/web/generalDisplay.html?id=new | LM386N-4/NOPB | /web/c/semiconductors/amplifiers-comparators/audio-amplifier-ics |
      | https://uk.rs-online.com/web/generalDisplay.html?id=new | 506-2097      | /web/p/rf-controllers/5062097/                                   |