Feature: technicalTest_bank
    Optional description of the feature


    #Taken pokemon STATS data ********************************************************************************************
    @testP
    Scenario: Taken pokemon Stats data
    Given I go to the PokemonUrl site
    Then The pokemon.json DOM information is loaded
    And I Search the pokemon charizard
    And I select the Pokemon: charizard
    And I compare the charizard web stats whit the data from the apiBaseURL

  #Taken pokemon ABILITIES data ****************************************************************************************
    @testP
    Scenario: Taken pokemon Abilities data
    Given I go to the PokemonUrl site
    Then The pokemon.json DOM information is loaded
    And I Search the pokemon pikachu
    And I select the Pokemon: pikachu
    And I compare the pikachu web abilities whit the data from the apiBaseURL


    #Taken pokemon TYPES data ****************************************************************************************
    @testP
    Scenario: Taken pokemon Types data
    Given I go to the PokemonUrl site
    Then The pokemon.json DOM information is loaded
    And I Search the pokemon bulbasaur
    And I select the Pokemon: bulbasaur
    And I compare the bulbasaur web types whit the data from the apiBaseURL