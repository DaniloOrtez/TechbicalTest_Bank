Feature: technicalTest_bank
    Optional description of the feature


    #Taken pokemon STATS data ********************************************************************************************
    @testP
    Scenario: Taken pokemon Stats data
    Given I go to the PokemonUrl site
    Then The pokemon.json DOM information is loaded
    And I Search data of charizard
    And I select the Pokemon: charizard
    And I compare the charizard web stats whit the data from the apiBaseURL

    #pikachu
    #bulbasaur

  #Taken pokemon ABILITIES data ****************************************************************************************
    @testP
    Scenario: Taken pokemon Abilities data
    Given I go to the PokemonUrl site
    Then The pokemon.json DOM information is loaded
    And I Search data of bulbasaur
    And I select the Pokemon: bulbasaur
    And I compare the bulbasaur web abilities whit the data from the apiBaseURL


    #Taken pokemon TYPES data ****************************************************************************************
    @testP
    Scenario: Taken pokemon Types data
    Given I go to the PokemonUrl site
    Then The pokemon.json DOM information is loaded
    And I Search data of bulbasaur
    And I select the Pokemon: bulbasaur
    And I compare the bulbasaur web types whit the data from the apiBaseURL


    #Taken specific pokemon ABILITY *******************************************************************************************
    @testP
    Scenario: Searching pokemon attack
    Given I go to the PokemonUrl site
    Then The pokemon.json DOM information is loaded
    And I Search data of Thunder Wave
    And I select the attack: Thunder Wave
    And I compare the Thunder Wave web effect_entries whit the data from the apiBaseURL

    #Ember
    #Air Slash

  #Taken pokemon ATTACK (Move) *****************************************************************************************
    @testP
    Scenario: Searching ability
    Given I go to the PokemonUrl site
    Then The pokemon.json DOM information is loaded
    And I do click in Tab Pokemon
    And I Search data of protean
    And I compare the protean web ability whit the data from the apiBaseURL

    #pixilate
    #plus