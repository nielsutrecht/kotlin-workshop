# Module C: Spring Boot

This module has a bare-bones Spring Boot service that needs a number of end-points implemented.

## Odd/even

An easy starter, just test an integer with the modulo operator.

### Test if a number is odd or even 

    GET /oddeven/{number}
    
### For a list of numbers get a map showing whether they are odd or even
    
    POST /oddeven/find
    
## Primes 

Are you going to take a brute force approach or are you going to implement a sieve?
    
### Test if a number is odd or even 
    
    GET /prime/{number}

### For a list of numbers get a list of those that are prime.

    POST /prime/find
    
## Fibonacci 

The most well known sequence of numbers, the fibonacci sequence. The first number is 0, the second is 1, the third is 1, the fourth is 2, etc. 
      
### Get the 'nth' fibonacci number  
    
    GET /fibonacci/{nths}

