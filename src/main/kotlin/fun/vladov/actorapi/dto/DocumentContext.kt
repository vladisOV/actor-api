package `fun`.vladov.actorapi.dto

/**
 * @author vladov
 * 06/07/2018
 */
class DocumentContext() {

    lateinit var endDate: String
    lateinit var startDate: String
    lateinit var resultSalary: String
    lateinit var salary: Number
    lateinit var series: String
    lateinit var hours: String
    lateinit var spellOutSalary: String
    lateinit var certNumber: String
    lateinit var contractNumber: String
    lateinit var contractDate: String
    lateinit var shortName: String
    lateinit var fullName: String
    lateinit var inn: String
    lateinit var bankName: String
    lateinit var bik: String
    lateinit var bankAccount: String
    lateinit var empAccount: String
    lateinit var billNumber: String


    constructor(endDate: String, startDate: String,
                resultSalary: String, hours: String,
                salary: Int, spellOutSalary: String,
                series: String, certNumber: String,
                contractNumber: String, contractDate: String,
                shortName: String, fullName: String) : this() {
        this.endDate = endDate
        this.startDate = startDate
        this.resultSalary = resultSalary
        this.hours = hours
        this.salary = salary
        this.series = series
        this.spellOutSalary = spellOutSalary
        this.certNumber = certNumber
        this.contractNumber = contractNumber
        this.contractDate = contractDate
        this.shortName = shortName
        this.fullName = fullName
    }

    constructor(endDate: String, startDate: String,
                resultSalary: String, hours: String,
                salary: Int, spellOutSalary: String,
                series: String, certNumber: String,
                contractNumber: String, contractDate: String,
                shortName: String, fullName: String, inn: String,
                bankName: String, bik: String, bankAccount: String,
                empAccount: String, billNumber: String) :
            this(endDate, startDate, resultSalary,
            hours, salary, spellOutSalary,
            series, certNumber, contractNumber,
                    contractDate, shortName, fullName) {
        this.bik = bik
        this.inn = inn
        this.bankAccount = bankAccount
        this.bankName = bankName
        this.empAccount = empAccount
        this.billNumber = billNumber
    }
}