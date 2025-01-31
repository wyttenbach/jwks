# JWKSCreator
This program creates a [JWKS](https://auth0.com/docs/secure/tokens/json-web-tokens/json-web-key-sets) file from PEM files.
I got a head start from [Google AI](Google.png), but the code provided was not correct and needed some TLC.

Instructions:
1. Create privateKey.pem and publicKey.pem locally (You can use [genkey.sh](genkey.sh) for this)
1. 'mvn compile exec:java' to run the code and generate the JWKS, which should look something like [this](jwk-sample.json)
