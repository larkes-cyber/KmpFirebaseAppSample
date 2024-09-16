//
//  Flow + Publisher.swift
//  iosApp
//
//  Created by MacBook on 16.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

import shared
import Combine



func sharePublisher<T>(_ flow: CommonFlow<T>) -> AnyPublisher<T, Never> {
    return Deferred<Publishers.HandleEvents<PassthroughSubject<T, Never>>> {
        let subject = PassthroughSubject<T, Never>()
        let closeable = flow.watch { next in
            if let next = next {
                subject.send(next)
            }
        }
        
        return subject.handleEvents(receiveCancel: {
            closeable.close()
        })
    }.eraseToAnyPublisher()
}
