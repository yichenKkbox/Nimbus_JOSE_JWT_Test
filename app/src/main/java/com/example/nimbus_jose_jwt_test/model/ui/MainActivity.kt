package com.example.nimbus_jose_jwt_test.model.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nimbus_jose_jwt_test.R
import com.nimbusds.jose.*
import com.nimbusds.jose.crypto.ECDHEncrypter
import com.nimbusds.jose.crypto.RSAEncrypter
import com.nimbusds.jwt.EncryptedJWT
import com.nimbusds.jwt.JWTClaimsSet
import java.security.KeyFactory
import java.security.interfaces.ECPublicKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec


//TODO("匯入Retrofit")
//TODO("將加密後的資料丟到server看結果跟加密前是否相同")
//TODO("測試改用ECDH")
class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()
    private val sample = "eyJhbGciOiJSU0EtT0FFUC01MTIiLCJlbmMiOiJBMjU2R0NNIn0.PC9YC_D7OMI3h4VnUF6jtcJVa6H8lr0wd5uMsEFueEX8A9aS0DTTDq1zW6OMKEXN3VO32jJFTWJpON1hmejXRVwnX1JbV7YGiZQxRErzyxL5ubjVVRNf1DcqvD3fLdIq8yk0jDUdBI0sBQ1d3y0VCnDhtpI1rDKWVDiQNZ6WH-YIa_CacYlZlLF_Y5ldOH6gFPzRGBWCIS5h21KUYWins-iZ-AICjWg-GYLU6ovX83mfgpPL_TY3_Yj6BmsyJHldbRN9mqLcYhv84qPgcvUywuZs004MRImR7Evg1vJyhIQRw4JIRKIFKcR7ZqjNLK_wNpnWhcjmA5GB3P_dVcQiag.DUT8mmUyLQI2Pel7.ovgnN6BEppZzjH0QnvipJcivmtdX806SLiJjMiBBC0xdBqaNtNVq7yG5KRVmA-9XbI5wjf-HKLWCEFD1XfBlLCdZyTIu2EKD3t9Z9ZwSb-snlf_78DM5mCgTDMo2Tnc5KKKKmpYCPRr-SBCclelRmOyJOzYO1ZZhuYJ0jrBAeFaM-vqdWrs69GTcAyMQihjCj1VZzvpxwCB_vIz02oEHORJvLVJtv2dmk9i7z4QqifRpFgpYADrQjpFHRkj4aBBIHvUkhzp1zxLmyebCBiPM_uxBplZ_ml8qUayFhUnc9YAshBez_a0hGzBvUzk7y5HJjAj0mt9UzT2DBdPa8As.MgJbn6K0IhpDLk2YNx5mSQ"
    private val _ecdhPublicKey = "MIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQBiJVNxcOmN5kBFcOi5FwOuABIZUcFM+jUOl3l9X5IKbDIvlAL+05TtfcZ/2GrI3VMmgdf+bdR22AJ/XRpCtA+G20AJkkyoN1uxH+wYwpukpPdx6E3XuH2zLvBICGdxe7hg0BFIWN9l7sySgS5DJoSNS1QJC8dSNKGyqEn/BvafgsJqr4="
    private val _publicKey =
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA16eC0/DFZz+gXuvapDbS1xIet5KD2X8Ep4XDQb54IDmUV9AsSabVckldu1lEXDeJT5iD3Y0qekB8UweAd87rSn3ODebHgLJbt3YeHmbdaWxr2cMnb4agbLHBfD5GK2lQkXJvjOjxKYOmUgSz0EcUwxt3YUeZBy4nqf7PKIX9Tf3P7+EeO2SUdREHaRJnhnp12Pb5wyC0tfIyPAqmlcI5LXqyQlyZbo0asvMYFrBoVJ8juzwQUNqWKI8GtmZwFQ5O2vNdCMd3GKgtVuLYaOR3VgmVoXMU7U3w9562Tbmpsj8i1W59TJK4y8omQDAgIKvlL85Up/uxUHaeSv3Up6GFVwIDAQAB"
    private val _publicKey2 =
        "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA16eC0/DFZz+gXuvapDbS\n" +
                "1xIet5KD2X8Ep4XDQb54IDmUV9AsSabVckldu1lEXDeJT5iD3Y0qekB8UweAd87r\n" +
                "Sn3ODebHgLJbt3YeHmbdaWxr2cMnb4agbLHBfD5GK2lQkXJvjOjxKYOmUgSz0EcU\n" +
                "wxt3YUeZBy4nqf7PKIX9Tf3P7+EeO2SUdREHaRJnhnp12Pb5wyC0tfIyPAqmlcI5\n" +
                "LXqyQlyZbo0asvMYFrBoVJ8juzwQUNqWKI8GtmZwFQ5O2vNdCMd3GKgtVuLYaOR3\n" +
                "VgmVoXMU7U3w9562Tbmpsj8i1W59TJK4y8omQDAgIKvlL85Up/uxUHaeSv3Up6GF\n" +
                "VwIDAQAB\n" +
                "-----END PUBLIC KEY-----"

    private val text = "{\n" +
            "  \"time\": 0,\n" +
            "  \"app_ver\": \"00.00.0000\",\n" +
            "  \"os\": \"android\",\n" +
            "  \"os_ver\": \"16.3.1\",\n" +
            "  \"records\": [\n" +
            "    {\n" +
            "      \"track\": {\n" +
            "        \"id\": \"string\",\n" +
            "        \"length\": 270000\n" +
            "      },\n" +
            "      \"play\": {\n" +
            "        \"time\": 1675319438196,\n" +
            "        \"progress\": 60000,\n" +
            "        \"status\": 1,\n" +
            "        \"mode\": \"on_demand\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        testEcdh(getJwtClaims())
        testRsa(getJwtClaims())
    }

    fun testEcdh(jwtClaims: JWTClaimsSet) {

        val ecPublicKey = readEcPublicKey()

        // Request JWT encrypted with RSA-OAEP-256 and 128-bit AES/GCM
        val header = JWEHeader(
            JWEAlgorithm.ECDH_ES,
            EncryptionMethod.A256GCM
        )

        // Create the encrypted JWT object
        var jwt = EncryptedJWT(header, jwtClaims).apply {
            encrypt(ECDHEncrypter(ecPublicKey))
        }

        // Serialise to JWT compact form
        val jwtString = jwt.serialize()

        viewModel.testEcdh(jwtString)
    }

    fun testRsa(jwtClaims: JWTClaimsSet) {

        val rsaPublicKey = readRsaPublicKey()

        // Request JWT encrypted with RSA-OAEP-512 and 256-bit AES/GCM
        val header = JWEHeader(
            JWEAlgorithm.RSA_OAEP_512,
            EncryptionMethod.A256GCM
        )

        // Create the encrypted JWT object
        var jwt = EncryptedJWT(header, jwtClaims).apply {
            encrypt(RSAEncrypter(rsaPublicKey))
        }

        // Serialise to JWT compact form
        val jwtString = jwt.serialize()

        viewModel.testRsa(jwtString)
    }

    private fun getJwtClaims(): JWTClaimsSet {
        return JWTClaimsSet.parse(text)
    }

    private fun readRsaPublicKey(): RSAPublicKey {
        val encoded = android.util.Base64.decode(_publicKey, android.util.Base64.DEFAULT)
        val keyFactory = KeyFactory.getInstance("RSA")
        val keySpec = X509EncodedKeySpec(encoded)
        return keyFactory.generatePublic(keySpec) as RSAPublicKey
    }

    private fun readEcPublicKey(): ECPublicKey {
//        val key = "-----BEGIN PUBLIC KEY-----\n" +
//                "MIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQBiJVNxcOmN5kBFcOi5FwOuABIZUcF\n" +
//                "M+jUOl3l9X5IKbDIvlAL+05TtfcZ/2GrI3VMmgdf+bdR22AJ/XRpCtA+G20AJkky\n" +
//                "oN1uxH+wYwpukpPdx6E3XuH2zLvBICGdxe7hg0BFIWN9l7sySgS5DJoSNS1QJC8d\n" +
//                "SNKGyqEn/BvafgsJqr4=\n" +
//                "-----END PUBLIC KEY-----"
//        val cert = X509CertUtils.parse(key)
//        println("cert.publicKey.format: ${cert.publicKey.format}")
//        return cert.publicKey as ECPublicKey

        val encoded = android.util.Base64.decode(_ecdhPublicKey, android.util.Base64.DEFAULT)
        val keyFactory = KeyFactory.getInstance("EC")
        val keySpec = X509EncodedKeySpec(encoded)
        return keyFactory.generatePublic(keySpec) as ECPublicKey
    }
}