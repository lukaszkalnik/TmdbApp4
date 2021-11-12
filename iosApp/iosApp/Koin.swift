//
//  Koin.swift
//  iosApp
//
//  Created by Lukasz on 12.11.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

private var _koin: Koin_coreKoin?
var koin: Koin_coreKoin {
    return _koin!
}

func startKoin() {
    _koin = CommonModuleKt.doInitKoin().koin
}
