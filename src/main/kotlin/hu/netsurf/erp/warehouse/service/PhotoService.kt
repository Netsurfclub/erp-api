package hu.netsurf.erp.warehouse.service

interface PhotoService<TFile, TPhoto> {
    fun uploadPhoto(file: TFile): TPhoto
}
