# ethereum-wallet-decryptor

ethereum-wallet-decryptor is a simple java command line application to decrypt an Ethereum wallet file. Wallet files in json format are supported.


### Build

The first step is to clone the repository to a local directory.

```
$ git clone https://github.com/steinhae/ethereum-wallet-decryptor.git
```

To build the jar change to the directory of the repository and execute the following command.

```
$ gradlew jar
```
After a successful build, the jar can be found in build/libs. 

### Usage

```
java -jar wallet-encrypter-1.0.jar
```

Command line parameter:<br>
-o,--output-file-path \<arg>  The credentials will be written to this file.<br>
-w,--wallet-file-path \<arg>   The file containing the wallet in json 

The password **cannot be passed** as a command line parameter for security reasons and has to be provided interactivly.

### Examples

Example wallet file (input): *wallet.json*
```
{"address":"51979cae42dcd802d2a24ac2b3b13fd957198740","crypto":{"cipher":"aes-128-ctr","ciphertext":"XXX","cipherparams":{"iv":"5ac2035ad061d5b514a9a38311c3ac17"},"kdf":"scrypt","kdfparams":{"dklen":32,"n":1024,"p":2048,"r":8,"salt":"a33af1489f1d97f44a69749a38a3c3193976cbbc4b273ce3b409a7f702defd2a"},"mac":"67445bfed9a56989914766ad611c30748a06864d9382baea881dabae50212cc7"},"id":"ec15c662-9a37-46d8-a786-7a6910a9da7b","version":3}
```
Example output: 
```
{"ecKeyPair":{"privateKey":XXX,"publicKey":XXX},"address":"0x51979cae42dcd802d2a24ac2b3b13fd957198740"}
```