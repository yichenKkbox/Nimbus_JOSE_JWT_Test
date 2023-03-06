package com.example.nimbus_jose_jwt_test.model.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nimbus_jose_jwt_test.R
import com.nimbusds.jose.*
import com.nimbusds.jose.crypto.RSAEncrypter
import com.nimbusds.jose.jwk.Curve
import com.nimbusds.jose.jwk.KeyUse
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.gen.OctetKeyPairGenerator
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator
import com.nimbusds.jwt.EncryptedJWT
import com.nimbusds.jwt.JWTClaimsSet
import java.util.*

//TODO("匯入Retrofit")
//TODO("將加密後的資料丟到server看結果跟加密前是否相同")
//TODO("測試改用ECDH")
class MainActivity : AppCompatActivity() {
    val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sample = "eyJhbGciOiJSU0EtT0FFUC01MTIiLCJlbmMiOiJBMjU2R0NNIn0.PC9YC_D7OMI3h4VnUF6jtcJVa6H8lr0wd5uMsEFueEX8A9aS0DTTDq1zW6OMKEXN3VO32jJFTWJpON1hmejXRVwnX1JbV7YGiZQxRErzyxL5ubjVVRNf1DcqvD3fLdIq8yk0jDUdBI0sBQ1d3y0VCnDhtpI1rDKWVDiQNZ6WH-YIa_CacYlZlLF_Y5ldOH6gFPzRGBWCIS5h21KUYWins-iZ-AICjWg-GYLU6ovX83mfgpPL_TY3_Yj6BmsyJHldbRN9mqLcYhv84qPgcvUywuZs004MRImR7Evg1vJyhIQRw4JIRKIFKcR7ZqjNLK_wNpnWhcjmA5GB3P_dVcQiag.DUT8mmUyLQI2Pel7.ovgnN6BEppZzjH0QnvipJcivmtdX806SLiJjMiBBC0xdBqaNtNVq7yG5KRVmA-9XbI5wjf-HKLWCEFD1XfBlLCdZyTIu2EKD3t9Z9ZwSb-snlf_78DM5mCgTDMo2Tnc5KKKKmpYCPRr-SBCclelRmOyJOzYO1ZZhuYJ0jrBAeFaM-vqdWrs69GTcAyMQihjCj1VZzvpxwCB_vIz02oEHORJvLVJtv2dmk9i7z4QqifRpFgpYADrQjpFHRkj4aBBIHvUkhzp1zxLmyebCBiPM_uxBplZ_ml8qUayFhUnc9YAshBez_a0hGzBvUzk7y5HJjAj0mt9UzT2DBdPa8As.MgJbn6K0IhpDLk2YNx5mSQ"

        val _publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA16eC0/DFZz+gXuvapDbS\n" +
                "1xIet5KD2X8Ep4XDQb54IDmUV9AsSabVckldu1lEXDeJT5iD3Y0qekB8UweAd87r\n" +
                "Sn3ODebHgLJbt3YeHmbdaWxr2cMnb4agbLHBfD5GK2lQkXJvjOjxKYOmUgSz0EcU\n" +
                "wxt3YUeZBy4nqf7PKIX9Tf3P7+EeO2SUdREHaRJnhnp12Pb5wyC0tfIyPAqmlcI5\n" +
                "LXqyQlyZbo0asvMYFrBoVJ8juzwQUNqWKI8GtmZwFQ5O2vNdCMd3GKgtVuLYaOR3\n" +
                "VgmVoXMU7U3w9562Tbmpsj8i1W59TJK4y8omQDAgIKvlL85Up/uxUHaeSv3Up6GF\n" +
                "VwIDAQAB"

        val ecdhPublicKey = OctetKeyPairGenerator(Curve.X25519)
            .keyUse(KeyUse.SIGNATURE) // indicate the intended use of the key (optional)
            .keyID(UUID.randomUUID().toString()) // give the key a unique ID (optional)
            .issueTime(Date()) // issued-at timestamp (optional)
            .generate()

        // Generate 2048-bit RSA key pair in JWK format, attach some metadata
        val rsaPublicKey: RSAKey = RSAKeyGenerator(2048)
//            .keyUse(KeyUse.SIGNATURE) // indicate the intended use of the key (optional)
//            .keyID(UUID.randomUUID().toString()) // give the key a unique ID (optional)
//            .issueTime(Date()) // issued-at timestamp (optional)
            .keyID(_publicKey)
            .generate()

        // Output the private and public RSA JWK parameters
        println("rsaPublicKey: $rsaPublicKey")

        // Output the public RSA JWK parameters only
        println("rsaPublicKey.toPublicJWK(): ${rsaPublicKey.toPublicJWK()}")

//        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA16eC0/DFZz+gXuvapDbS1xIet5KD2X8Ep4XDQb54IDmUV9AsSabVckldu1lEXDeJT5iD3Y0qekB8UweAd87rSn3ODebHgLJbt3YeHmbdaWxr2cMnb4agbLHBfD5GK2lQkXJvjOjxKYOmUgSz0EcUwxt3YUeZBy4nqf7PKIX9Tf3P7+EeO2SUdREHaRJnhnp12Pb5wyC0tfIyPAqmlcI5LXqyQlyZbo0asvMYFrBoVJ8juzwQUNqWKI8GtmZwFQ5O2vNdCMd3GKgtVuLYaOR3VgmVoXMU7U3w9562Tbmpsj8i1W59TJK4y8omQDAgIKvlL85Up/uxUHaeSv3Up6GFVwIDAQAB"

        // Compose the JWT claims set
        val now = Date()

        val jwtClaims: JWTClaimsSet = JWTClaimsSet.Builder()
//            .issuer("https://openid.net")
//            .subject("alice")
//            .audience(Arrays.asList("https://app-one.com", "https://app-two.com"))
//            .expirationTime(Date(now.getTime() + 1000 * 60 * 10)) // expires in 10 minutes
//            .notBeforeTime(now)
//            .issueTime(now)
//            .jwtID(UUID.randomUUID().toString())
            .build()

        println("jwtClaims: $jwtClaims")

        // Request JWT encrypted with RSA-OAEP-256 and 128-bit AES/GCM
        val header = JWEHeader(
            JWEAlgorithm.RSA_OAEP_512,
            EncryptionMethod.A256GCM
        )

        // Create the encrypted JWT object
        var jwt = EncryptedJWT(header, jwtClaims)

        // Create an encrypter with the specified public RSA key
        val encrypter = RSAEncrypter(rsaPublicKey)

        // Do the actual encryption
        jwt.encrypt(encrypter)

        // Serialise to JWT compact form
        val jwtString = jwt.serialize()

        println("jwtString: $jwtString")
//        // Produces
//        //
//        // eyJhbGciOiJSU0EtT0FFUCIsImVuYyI6IkExMjhHQ00ifQ.K52jFwAQJH-
//        // DxMhtaq7sg5tMuot_mT5dm1DR_01wj6ZUQQhJFO02vPI44W5nDjC5C_v4p
//        // W1UiJa3cwb5y2Rd9kSvb0ZxAqGX9c4Z4zouRU57729ML3V05UArUhck9Zv
//        // ssfkDW1VclingL8LfagRUs2z95UkwhiZyaKpmrgqpKX8azQFGNLBvEjXnx
//        // -xoDFZIYwHOno290HOpig3aUsDxhsioweiXbeLXxLeRsivaLwUWRUZfHRC
//        // _HGAo8KSF4gQZmeJtRgai5mz6qgbVkg7jPQyZFtM5_ul0UKHE2y0AtWm8I
//        // zDE_rbAV14OCRZJ6n38X5urVFFE5sdphdGsNlA.gjI_RIFWZXJwaO9R.oa
//        // E5a-z0N1MW9FBkhKeKeFa5e7hxVXOuANZsNmBYYT8G_xlXkMD0nz4fIaGt
//        // uWd3t9Xp-kufvvfD-xOnAs2SBX_Y1kYGPto4mibBjIrXQEjDsKyKwndxzr
//        // utN9csmFwqWhx1sLHMpJkgsnfLTi9yWBPKH5Krx23IhoDGoSfqOquuhxn0
//        // y0WkuqH1R3z-fluUs6sxx9qx6NFVS1NRQ-LVn9sWT5yx8m9AQ_ng8MBWz2
//        // BfBTV0tjliV74ogNDikNXTAkD9rsWFV0IX4IpA.sOLijuVySaKI-FYUaBy
//        // wpg
//
//        // Parse
//        jwt = EncryptedJWT.parse(jwtString)
//
//        // Create a decrypter with the specified private RSA key
//        val decrypter = RSADecrypter(privateKey)
//
//        // Decrypt
//        jwt.decrypt(decrypter)

        viewModel.testRsa(sample)
    }
}