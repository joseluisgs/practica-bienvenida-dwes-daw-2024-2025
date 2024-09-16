package org.example

import kotlinx.serialization.decodeFromString
import nl.adaptivity.xmlutil.serialization.XML
import org.example.tenist.dto.TenistDto
import org.example.tenist.mappers.toTenist
import org.example.tenist.models.Dexterity
import org.example.tenist.models.Tenist
import org.example.tenist.service.storage.TenistStorageImpl
import org.example.tenist.service.storage.csv.TenistStorageCsvImpl
import org.example.tenist.service.storage.json.TenistStorageJsonImpl
import org.example.tenist.service.storage.xml.TenistStorageXmlImpl
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {
}