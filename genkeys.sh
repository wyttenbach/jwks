#!/bin/bash
function die { 
    if [ $# -gt 0 ]; then
        echo "$@";
    fi;
    exit 1
}

openssl genrsa -out privateKey.pem 512 || die
openssl rsa -in privateKey.pem -pubout -out publicKey.pem || die

# Now, look for a tool that converts .pem to jwk(s) format.
# One of the tools is https://pem2jwk.vercel.app
# Select Signing Algorithm: RS256
# Select Public Key Use: Signing
# Key ID: alpha-numeric-random-string or leave blank to generate a random one.
# PEM encoded key: {paste-the-publicKey-pem-file-content-here
# Click on Convert to JWK button.

# Now, save the output in a .json file. This is your JWKS Endpoint,
# which you need to put on the Web3Auth Dashboard.


# Source:
# https://web3auth.io/community/t/how-to-create-jwks-from-pem-format/2718/6

