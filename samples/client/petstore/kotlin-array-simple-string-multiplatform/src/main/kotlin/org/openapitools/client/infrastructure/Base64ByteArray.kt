package org.openapitools.client.infrastructure

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(Base64ByteArray.Companion::class)
class Base64ByteArray(val value: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as Base64ByteArray
        return value.contentEquals(other.value)
    }

    override fun hashCode(): Int = value.contentHashCode()

    override fun toString() = "${Base64ByteArray::class.simpleName}(${hex(value)})"

    @Serializer(Base64ByteArray::class)
    companion object : KSerializer<Base64ByteArray> {
        override val descriptor = PrimitiveSerialDescriptor("Base64ByteArray", PrimitiveKind.STRING)
        override fun serialize(encoder: Encoder, value: Base64ByteArray) =
            encoder.encodeString(value.value.encodeBase64())

        override fun deserialize(decoder: Decoder) = Base64ByteArray(decoder.decodeString().decodeBase64Bytes())
    }
}