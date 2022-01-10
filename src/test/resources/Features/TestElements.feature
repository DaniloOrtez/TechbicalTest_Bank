Feature: technicalTest_bank
    Optional description of the feature


    #Taken pokemon STATS data ********************************************************************************************
    @testP
    Scenario: Taken pokemon Stats data
    Given I go to the PokemonUrl site
    Then The pokemon.json DOM information is loaded
    And I set the txtSearch with the value pikachu
    And I select Pikachu
    And I compare the pikachu web stats whit the data from the apiBaseURL

  #Taken pokemon ABILITIES data ****************************************************************************************
    @testP
    Scenario: Taken pokemon Abilities data
    Given I go to the PokemonUrl site
    Then The pokemon.json DOM information is loaded
    And I set the txtSearch with the value pikachu
    And I select Pikachu
    And I compare the pikachu web abilities whit the data from the apiBaseURL